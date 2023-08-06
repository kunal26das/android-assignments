package io.github.kunal26das.epifi.util

import androidx.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import coil.load

object ViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("visible")
    fun View.setIsVisible(isVisible: Boolean?) {
        this.isVisible = isVisible ?: false
    }

}