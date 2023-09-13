package io.github.kunal26das.common

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

object ViewBindingAdapter {
    @JvmStatic
    @BindingAdapter("visible")
    fun View.setIsVisible(isVisible: Boolean?) {
        this.isVisible = isVisible ?: false
    }
}