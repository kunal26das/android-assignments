package io.github.kunal26das.epifi.home

import android.content.ContextWrapper
import androidx.annotation.MainThread
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlin.reflect.KClass

abstract class ViewHolder<T : ViewDataBinding>(binding: T) : ViewHolder(binding.root) {

    protected val context
        get() = itemView.context

    private fun getActivity(): FragmentActivity? {
        var context = itemView.context
        while (context is ContextWrapper) {
            if (context is FragmentActivity) return context
            context = context.baseContext
        }
        return null
    }

    fun requireActivity(): FragmentActivity {
        return getActivity()
            ?: throw IllegalStateException("ViewHolder $this not attached to an activity.")
    }

    @MainThread
    protected fun <VM : ViewModel> createViewModelLazy(
        viewModelClass: KClass<VM>,
        storeProducer: () -> ViewModelStore,
        extrasProducer: () -> CreationExtras = { requireActivity().defaultViewModelCreationExtras },
        factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ): Lazy<VM> {
        val factoryPromise =
            factoryProducer ?: { requireActivity().defaultViewModelProviderFactory }
        return ViewModelLazy(viewModelClass, storeProducer, factoryPromise, extrasProducer)
    }

    @MainThread
    protected inline fun <reified VM : ViewModel> viewModels(
        noinline extrasProducer: (() -> CreationExtras)? = null,
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ): Lazy<VM> = createViewModelLazy(
        VM::class, { requireActivity().viewModelStore },
        { extrasProducer?.invoke() ?: requireActivity().defaultViewModelCreationExtras },
        factoryProducer ?: { requireActivity().defaultViewModelProviderFactory }
    )
}