package io.github.kunal26das.assignment.init

import android.content.Context
import com.facebook.stetho.Stetho
import io.github.kunal26das.common.Initializer

class StethoInitializer : Initializer<Context>() {
    override fun create(context: Context): Context {
        Stetho.initializeWithDefaults(context)
        return context
    }
}