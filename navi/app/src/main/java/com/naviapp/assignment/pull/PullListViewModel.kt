package com.naviapp.assignment.pull

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.naviapp.assignment.BuildConfig
import com.naviapp.assignment.Constant
import com.naviapp.assignment.model.Pull
import com.naviapp.assignment.model.Repo
import com.naviapp.assignment.source.PullPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
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
            PullPagingSource(pullRepository, BuildConfig.USER_ID, repo, _currentList)
        }
    ).liveData.cachedIn(viewModelScope)

}