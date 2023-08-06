package io.github.kunal26das.common

import androidx.startup.Initializer

abstract class Initializer<T> : Initializer<T> {
    override fun dependencies() = mutableListOf<Class<out Initializer<*>>>()
}