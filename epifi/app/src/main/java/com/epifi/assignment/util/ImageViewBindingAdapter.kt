package com.epifi.assignment.util

import androidx.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
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
        load(url) {

        }
    }

}