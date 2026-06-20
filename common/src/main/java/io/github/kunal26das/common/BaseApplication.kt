package io.github.kunal26das.common

import android.app.Application
import com.google.android.material.color.DynamicColors

open class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}
