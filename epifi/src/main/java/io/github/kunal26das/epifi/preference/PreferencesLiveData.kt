package io.github.kunal26das.epifi.preference

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import io.github.kunal26das.epifi.model.Preferences
import io.github.kunal26das.epifi.model.SearchType
import io.github.kunal26das.epifi.preference.PreferenceModule.KEY_SEARCH_QUERY
import io.github.kunal26das.epifi.preference.PreferenceModule.KEY_SEARCH_TYPE
import io.github.kunal26das.epifi.preference.PreferenceModule.KEY_SHOW_BOOKMARKS
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