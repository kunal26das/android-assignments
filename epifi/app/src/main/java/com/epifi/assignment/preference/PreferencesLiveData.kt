package com.epifi.assignment.preference

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.epifi.assignment.model.Preferences
import com.epifi.assignment.model.SearchType
import com.epifi.assignment.preference.PreferenceModule.KEY_SEARCH_QUERY
import com.epifi.assignment.preference.PreferenceModule.KEY_SEARCH_TYPE
import com.epifi.assignment.preference.PreferenceModule.KEY_SHOW_BOOKMARKS
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Named

class PreferencesLiveData @Inject constructor(
    @Named(KEY_SEARCH_QUERY) searchQuery: MutableLiveData<String>,
    @Named(KEY_SEARCH_TYPE) searchType: MutableLiveData<SearchType>,
    @Named(KEY_SHOW_BOOKMARKS) showBookmarks: MutableLiveData<Boolean>,
) : MediatorLiveData<Preferences>() {

    private var job: Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    init {
        value = Preferences(
            searchQuery = searchQuery.value,
            searchType = searchType.value,
            showBookmarks = showBookmarks.value ?: false
        )
        addSource(searchQuery) {
            value?.searchQuery = it
            refresh()
        }
        addSource(searchType) {
            value?.searchType = it
            refresh()
        }
        addSource(showBookmarks) {
            value?.showBookmarks = it ?: false
            refresh()
        }
    }

    private fun refresh() {
        job?.cancel()
        job = coroutineScope.launch {
            delay(500)
            value = value
        }
    }

}