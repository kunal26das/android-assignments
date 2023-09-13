package io.github.kunal26das.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

object ImageViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("srcCompat")
    fun ImageView.setSrcCompat(drawable: Drawable?) {
        setImageDrawable(drawable)
    }

    @JvmStatic
    @BindingAdapter("srcCompat")
    fun ImageView.setSrcCompat(url: String?) {
        load(url)
    }
}