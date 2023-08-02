package com.naviapp.assignment.source

import com.naviapp.assignment.Constant.INITIAL_PAGE
import com.naviapp.assignment.model.Repo
import com.naviapp.assignment.repo.RepoRepository

class RepoPagingSource(
    private val repoRepository: RepoRepository,
    private val user: String,
) : PagingSource<Repo>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Repo> {
        val page = params.key ?: INITIAL_PAGE
        val limit = params.loadSize
        return try {
            val repoList = repoRepository.getRepoList(
                user = user,
                page = page,
                perPage = limit,
            )
            LoadResult.Page(
                repoList,
                if (page > INITIAL_PAGE) page - 1 else null,
                if (repoList.isNotEmpty()) page + 1 else null,
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

}