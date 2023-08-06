package io.github.kunal26das.navi.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import io.github.kunal26das.navi.Constant
import io.github.kunal26das.navi.source.RepoPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val repoRepository: RepoRepository
) : ViewModel() {

    val repoListPager = Pager(
        config = PagingConfig(
            pageSize = Constant.DEFAULT_PER_PAGE,
            initialLoadSize = Constant.DEFAULT_PER_PAGE,
            enablePlaceholders = true,
        ),
        pagingSourceFactory = {
            RepoPagingSource(repoRepository, Constant.USER_ID)
        }
    ).liveData.cachedIn(viewModelScope)
}