package com.epifi.assignment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.epifi.assignment.network.NetworkModule.PAGE_SIZE
import com.epifi.assignment.preference.PreferencesLiveData
import com.epifi.assignment.repository.OmdbRepository
import com.epifi.assignment.source.ElementPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val omdbRepository: OmdbRepository,
    preferencesLiveData: PreferencesLiveData,
) : ViewModel() {

    val pager = preferencesLiveData.switchMap {
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                enablePlaceholders = true,
            ),
            pagingSourceFactory = {
                when (it.showBookmarks) {
                    true -> omdbRepository.getBookmarks(it)
                    false -> ElementPagingSource(omdbRepository, it)
                }
            }
        ).liveData.cachedIn(viewModelScope)
    }

}