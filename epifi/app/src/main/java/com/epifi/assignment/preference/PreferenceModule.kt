package com.epifi.assignment.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.epifi.assignment.model.SearchType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    const val KEY_SEARCH_TYPE = "search_type"
    const val KEY_SEARCH_QUERY = "search_query"
    const val KEY_SHOW_BOOKMARKS = "show_bookmarks"

    private fun getSharedPreferences(
        @ApplicationContext context: Context
    ) = context.getSharedPreferences(context.javaClass.simpleName, Context.MODE_PRIVATE)!!

    private val SharedPreferences.editor get() = edit()

    private inline operator fun <reified T> SharedPreferences.get(key: String?): T? {
        return when {
            !contains(key) -> null
            else -> when (T::class) {
                String::class -> getString(key, "")
                Boolean::class -> getBoolean(key, false)
                else -> null
            } as? T
        }
    }

    private operator fun SharedPreferences.Editor.set(key: String, value: Any?) {
        when (value) {
            null -> remove(key)
            else -> when (value) {
                is String -> putString(key, value).apply()
                is Boolean -> putBoolean(key, value).apply()
            }
        }
    }

    private inline fun <reified T> getMutableLiveData(
        @ApplicationContext context: Context, key: String,
    ): MutableLiveData<T?> {
        val sharedPreferences = getSharedPreferences(context)
        val sharedPreferencesEditor = sharedPreferences.editor
        val value = sharedPreferences.get<T>(key)
        return object : MutableLiveData<T?>(value),
            SharedPreferences.OnSharedPreferenceChangeListener {

            private val key = key

            override fun onActive() {
                super.onActive()
                sharedPreferences.registerOnSharedPreferenceChangeListener(this)
            }

            override fun setValue(value: T?) {
                super.setValue(value)
                sharedPreferencesEditor[key] = value
            }

            override fun onSharedPreferenceChanged(
                sharedPreferences: SharedPreferences?,
                key: String?
            ) {
                if (this.key == key) {
                    setValue(sharedPreferences?.get<T>(key))
                }
            }

            override fun onInactive() {
                super.onInactive()
                sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
            }
        }
    }

    @Provides
    @Named(KEY_SEARCH_QUERY)
    fun getSearchQuery(@ApplicationContext context: Context): MutableLiveData<String?> {
        return getMutableLiveData(context, KEY_SEARCH_QUERY)
    }

    @Provides
    @Named(KEY_SEARCH_TYPE)
    fun getSearchType(@ApplicationContext context: Context): MutableLiveData<SearchType?> {
        val searchType = getMutableLiveData<String?>(context, KEY_SEARCH_TYPE)
        return object : MediatorLiveData<SearchType>() {
            init {
                addSource(searchType) {
                    val newValue = SearchType[it]
                    if (value != newValue) {
                        value = newValue
                    }
                }
            }

            override fun setValue(value: SearchType?) {
                super.setValue(value)
                val newValue = value?.name
                if (searchType.value != newValue) {
                    searchType.value = newValue
                }
            }
        }
    }

    @Provides
    @Named(KEY_SHOW_BOOKMARKS)
    fun getShowBookmarks(@ApplicationContext context: Context): MutableLiveData<Boolean?> {
        return getMutableLiveData(context, KEY_SHOW_BOOKMARKS)
    }

}