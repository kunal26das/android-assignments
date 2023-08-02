package com.naviapp.assignment.source

import androidx.lifecycle.MutableLiveData
import com.naviapp.assignment.Constant.INITIAL_PAGE
import com.naviapp.assignment.model.Pull
import com.naviapp.assignment.model.Repo
import com.naviapp.assignment.pull.PullRepository

class PullPagingSource(
    private val pullRepository: PullRepository,
    private val owner: String,
    private val repo: Repo?,
    private val currentList: MutableLiveData<List<Pull>>? = null,
) : PagingSource<Pull>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Pull> {
        val page = params.key ?: INITIAL_PAGE
        val limit = params.loadSize
        return try {
            val pullList = pullRepository.getPullList(
                owner = owner,
                repo = repo,
                page = page,
                perPage = limit,
            )
            currentList?.postValue(pullList)
            LoadResult.Page(
                pullList,
                if (page > INITIAL_PAGE) page - 1 else null,
                if (pullList.isNotEmpty()) page + 1 else null,
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

}