package com.naviapp.assignment.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindingImpl<T : ViewDataBinding>(
    @LayoutRes layoutId: Int,
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup? = null,
) {

    val root get() = binding.root

    protected val binding = DataBindingUtil.inflate<T>(
        layoutInflater, layoutId, viewGroup, false
    )!!

}