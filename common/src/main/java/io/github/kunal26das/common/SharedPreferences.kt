package io.github.kunal26das.common

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData

val SharedPreferences.editor get() = edit()

inline operator fun <reified T> SharedPreferences.get(key: String?): T? {
    return when {
        !contains(key) -> null
        else -> when (T::class) {
            String::class -> getString(key, "")
            Boolean::class -> getBoolean(key, false)
            else -> null
        } as? T
    }
}

operator fun SharedPreferences.Editor.set(key: String, value: Any?) {
    when (value) {
        null -> remove(key)
        else -> when (value) {
            is String -> putString(key, value).apply()
            is Boolean -> putBoolean(key, value).apply()
        }
    }
}

inline fun <reified T> SharedPreferences.getMutableLiveData(key: String): MutableLiveData<T?> {
    return object : MutableLiveData<T?>(get(key)),
        SharedPreferences.OnSharedPreferenceChangeListener {

        private val key = key

        override fun onActive() {
            registerOnSharedPreferenceChangeListener(this)
        }

        override fun setValue(value: T?) {
            super.setValue(value)
            editor[key] = value
        }

        override fun onSharedPreferenceChanged(
            sharedPreferences: SharedPreferences?,
            key: String?
        ) {
            if (this.key == key) {
                value = get<T>(key)
            }
        }

        override fun onInactive() {
            unregisterOnSharedPreferenceChangeListener(this)
        }
    }
}