package io.github.kunal26das.navi.repo

import io.github.kunal26das.navi.service.RepoService
import javax.inject.Inject

class RepoRepository @Inject constructor(
    private val repoService: RepoService
) {
    suspend fun getRepoList(
        user: String,
        perPage: Int,
        page: Int,
    ) = repoService.getRepoList(
        user = user,
        perPage = perPage,
        page = page,
    )
}