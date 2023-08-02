package com.epifi.assignment.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindingImpl<T : ViewDataBinding>(
    layoutInflater: LayoutInflater,
    @LayoutRes layoutId: Int,
    viewGroup: ViewGroup? = null,
    attachToParent: Boolean = false,
) {

    val root get() = binding.root

    protected val binding = DataBindingUtil.inflate<T>(
        layoutInflater, layoutId, viewGroup, attachToParent
    )!!

}