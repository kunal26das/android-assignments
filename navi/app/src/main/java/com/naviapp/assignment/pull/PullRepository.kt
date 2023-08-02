package com.naviapp.assignment.pull

import com.naviapp.assignment.model.Repo
import com.naviapp.assignment.service.PullService
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