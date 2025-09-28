package io.github.kunal26das.alle

import android.app.Application
import com.google.android.material.color.DynamicColors

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}