package com.naviapp.assignment.service

import com.naviapp.assignment.Constant.DEFAULT_PER_PAGE
import com.naviapp.assignment.Constant.INITIAL_PAGE
import com.naviapp.assignment.model.Pull
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PullService {

    @GET("/repos/{owner}/{repo}/pulls")
    suspend fun getPullList(
        /**
         * The account owner of the repository. The name is not case sensitive.
         */
        @Path("owner") owner: String,

        /**
         * The name of the repository. The name is not case sensitive.
         */
        @Path("repo") repo: String?,

        /**
         * Either open, closed, or all to filter by state.
         * Default: open
         * Can be one of: open, closed, all
         */
        @Query("state") state: String? = "closed",

        /**
         * Filter pulls by head user or head organization and branch name
         * in the format of user:ref-name or organization:ref-name.
         * For example: github:new-script-format or octocat:test-branch.
         */
        @Query("head") head: String? = null,

        /**
         * Filter pulls by base branch name. Example: gh-pages.
         */
        @Query("base") base: String? = null,

        /**
         * What to sort results by.
         *
         * popularity will sort by the number of comments.
         *
         * long-running will sort by date created
         * and will limit the results to pull requests that have been open for more than a month
         * and have had activity within the past month.
         *
         * Default: created
         * Can be one of: created, updated, popularity, long-running
         */
        @Query("sort") sort: String = "created",

        /**
         * The direction of the sort.
         * Default: desc when sort is created or sort is not specified, otherwise asc.
         * Can be one of: asc, desc
         */
        @Query("direction") direction: String = "desc",

        /**
         * The number of results per page (max 100).
         * Default: 30
         */
        @Query("per_page") perPage: Int = DEFAULT_PER_PAGE,

        /**
         * Page number of the results to fetch.
         * Default: 1
         */
        @Query("page") page: Int = INITIAL_PAGE,
    ): List<Pull>

}