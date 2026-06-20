package io.github.kunal26das.epifi.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kunal26das.epifi.model.Element
import io.github.kunal26das.epifi.model.SearchType
import io.github.kunal26das.epifi.network.NetworkModule.PAGE_SIZE
import io.github.kunal26das.epifi.preference.PreferenceModule.KEY_SEARCH_QUERY
import io.github.kunal26das.epifi.preference.PreferenceModule.KEY_SEARCH_TYPE
import io.github.kunal26das.epifi.preference.PreferenceModule.KEY_SHOW_BOOKMARKS
import io.github.kunal26das.epifi.preference.PreferencesLiveData
import io.github.kunal26das.epifi.repository.OmdbRepository
import io.github.kunal26das.epifi.source.ElementPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val omdbRepository: OmdbRepository,
    preferencesLiveData: PreferencesLiveData,
    @param:Named(KEY_SEARCH_QUERY) val searchQuery: MutableLiveData<String?>,
    @param:Named(KEY_SEARCH_TYPE) val searchType: MutableLiveData<SearchType?>,
    @param:Named(KEY_SHOW_BOOKMARKS) val showBookmarks: MutableLiveData<Boolean?>,
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val pager: Flow<PagingData<Element>> = preferencesLiveData.asFlow()
        .flatMapLatest { preferences ->
            Pager(
                config = PagingConfig(
                    pageSize = PAGE_SIZE,
                    initialLoadSize = PAGE_SIZE,
                    enablePlaceholders = true,
                ),
                pagingSourceFactory = {
                    when (preferences.showBookmarks) {
                        true -> omdbRepository.getBookmarks(preferences)
                        false -> ElementPagingSource(omdbRepository, preferences)
                    }
                }
            ).flow
        }.cachedIn(viewModelScope)

    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    fun setSearchType(type: SearchType?) {
        searchType.value = type
    }

    fun setShowBookmarks(show: Boolean) {
        showBookmarks.value = show
    }

    suspend fun isBookmarked(element: Element) = withContext(Dispatchers.IO) {
        omdbRepository.isElementBookmarked(element)
    }

    fun toggleBookmark(element: Element) {
        viewModelScope.launch(Dispatchers.IO) {
            when (element.isBookmarked) {
                true -> omdbRepository.insertElement(element)
                false -> omdbRepository.deleteElement(element)
            }
        }
    }

    private val _detail = MutableStateFlow<Element?>(null)
    val detail = _detail.asStateFlow()

    private val _detailLoading = MutableStateFlow(false)
    val detailLoading = _detailLoading.asStateFlow()

    fun loadDetail(element: Element?) {
        _detail.value = element
        _detailLoading.value = true
        viewModelScope.launch(Dispatchers.Default) {
            try {
                _detail.value = omdbRepository.getElement(element)
            } catch (_: Throwable) {
            }
            _detailLoading.value = false
        }
    }

    fun clearDetail() {
        _detail.value = null
    }
}
