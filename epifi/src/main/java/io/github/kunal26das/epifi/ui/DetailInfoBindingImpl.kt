package io.github.kunal26das.epifi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import io.github.kunal26das.common.BindingImpl
import io.github.kunal26das.epifi.R
import io.github.kunal26das.epifi.databinding.FragmentDetailInfoBinding
import io.github.kunal26das.epifi.model.Element

class DetailInfoBindingImpl(
    layoutInflater: LayoutInflater,
    container: ViewGroup?
) : BindingImpl<FragmentDetailInfoBinding>(
    layoutInflater, R.layout.fragment_detail_info, container,
) {

    private var onCloseClickListener: View.OnClickListener? = null

    init {
        binding.close.setOnClickListener {
            onCloseClickListener?.onClick(it)
        }
    }

    fun bind(
        element: Element?,
        lifecycleOwner: LifecycleOwner? = null
    ) {
        binding.element = element
        binding.executePendingBindings()
        if (lifecycleOwner != null) {
            binding.lifecycleOwner = lifecycleOwner
        }
    }

    fun setIsLoading(isVisible: Boolean?) {
        binding.loading.isVisible = isVisible ?: false
    }

    fun setOnCloseClickListener(
        onCloseClickListener: View.OnClickListener
    ) {
        this.onCloseClickListener = onCloseClickListener
    }
}