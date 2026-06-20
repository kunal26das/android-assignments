package io.github.kunal26das.epifi

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import dagger.hilt.android.HiltAndroidApp
import io.github.kunal26das.common.BaseApplication

@HiltAndroidApp
class Application : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(
                remoteConfigSettings {
                    minimumFetchIntervalInSeconds = 3600
                }
            )
            setDefaultsAsync(R.xml.remote_config_defaults)
            fetchAndActivate()
        }
    }
}
