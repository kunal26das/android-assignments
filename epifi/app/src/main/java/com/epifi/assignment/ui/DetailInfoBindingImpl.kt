package com.epifi.assignment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.epifi.assignment.R
import com.epifi.assignment.databinding.FragmentDetailInfoBinding
import com.epifi.assignment.model.Element
import com.epifi.assignment.util.BindingImpl

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