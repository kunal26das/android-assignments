package io.github.kunal26das.navi.pull

import io.github.kunal26das.navi.model.Repo
import io.github.kunal26das.navi.service.PullService
import javax.inject.Inject

class PullRepository @Inject constructor(
    private val pullService: PullService
) {
    suspend fun getPullList(
        owner: String,
        repo: Repo?,
        page: Int,
        perPage: Int,
    ) = pullService.getPullList(
        owner = owner,
        repo = repo?.name,
        page = page,
        perPage = perPage,
    )
}