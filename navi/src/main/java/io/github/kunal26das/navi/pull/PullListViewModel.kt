package io.github.kunal26das.navi.pull

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kunal26das.navi.Constant
import io.github.kunal26das.navi.model.Pull
import io.github.kunal26das.navi.model.Repo
import io.github.kunal26das.navi.source.PullPagingSource
import javax.inject.Inject

@HiltViewModel
class PullListViewModel @Inject constructor(
    private val pullRepository: PullRepository
) : ViewModel() {

    private val _currentList = MutableLiveData<List<Pull>>()
    val currentList: LiveData<List<Pull>> get() = _currentList

    fun getPullPager(repo: Repo?) = Pager(
        config = PagingConfig(
            pageSize = Constant.DEFAULT_PER_PAGE,
            initialLoadSize = Constant.DEFAULT_PER_PAGE,
            enablePlaceholders = true,
        ),
        pagingSourceFactory = {
            PullPagingSource(pullRepository, Constant.USER_ID, repo, _currentList)
        }
    ).flow.cachedIn(viewModelScope)
}