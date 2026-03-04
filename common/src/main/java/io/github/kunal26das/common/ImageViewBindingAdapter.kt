package io.github.kunal26das.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil3.load

object ImageViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageRes")
    fun ImageView.setImageRes(resId: Int) {
        if (resId != 0) setImageResource(resId)
    }

    @JvmStatic
    @BindingAdapter("imageRes")
    fun ImageView.setImageRes(drawable: Drawable?) {
        setImageDrawable(drawable)
    }

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