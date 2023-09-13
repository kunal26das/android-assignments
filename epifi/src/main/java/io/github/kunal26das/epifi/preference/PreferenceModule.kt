package io.github.kunal26das.epifi.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.kunal26das.common.getMutableLiveData
import io.github.kunal26das.epifi.model.SearchType
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    const val KEY_SEARCH_TYPE = "search_type"
    const val KEY_SEARCH_QUERY = "search_query"
    const val KEY_SHOW_BOOKMARKS = "show_bookmarks"

    @Provides
    fun getSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(context.javaClass.simpleName, Context.MODE_PRIVATE)!!
    }

    @Provides
    @Named(KEY_SEARCH_QUERY)
    fun getSearchQuery(
        sharedPreferences: SharedPreferences
    ): MutableLiveData<String?> {
        return sharedPreferences.getMutableLiveData(KEY_SEARCH_QUERY)
    }

    @Provides
    @Named(KEY_SEARCH_TYPE)
    fun getSearchType(
        sharedPreferences: SharedPreferences
    ): MutableLiveData<SearchType?> {
        val searchType = sharedPreferences.getMutableLiveData<String?>(KEY_SEARCH_TYPE)
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
    fun getShowBookmarks(
        sharedPreferences: SharedPreferences
    ): MutableLiveData<Boolean?> {
        return sharedPreferences.getMutableLiveData(KEY_SHOW_BOOKMARKS)
    }
}