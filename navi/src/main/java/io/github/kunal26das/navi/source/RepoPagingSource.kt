package io.github.kunal26das.navi.source

import io.github.kunal26das.navi.Constant.INITIAL_PAGE
import io.github.kunal26das.navi.model.Repo
import io.github.kunal26das.navi.repo.RepoRepository

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