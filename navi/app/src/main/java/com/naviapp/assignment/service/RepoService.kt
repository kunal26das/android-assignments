package com.naviapp.assignment.service

import com.naviapp.assignment.Constant.DEFAULT_PER_PAGE
import com.naviapp.assignment.Constant.INITIAL_PAGE
import com.naviapp.assignment.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RepoService {

    @GET("/users/{user}/repos")
    suspend fun getRepoList(
        /**
         * The organization name. The name is not case sensitive.
         */
        @Path("user") user: String,

        /**
         * Specifies the types of repositories you want returned.
         * Can be one of: all, public, private, forks, sources, member, internal
         */
        @Query("type") type: String = "public",

        /**
         * The property to sort the results by.
         * Can be one of: created, updated, pushed, full_name
         */
        @Query("sort") sort: String = "full_name",

        /**
         * The order to sort by.
         * Default: asc when using full_name, otherwise desc.
         */
        @Query("direction") direction: String = "asc",

        /**
         * The number of results per page (max 100).
         */
        @Query("per_page") perPage: Int = DEFAULT_PER_PAGE,

        /**
         * Page number of the results to fetch.
         */
        @Query("page") page: Int = INITIAL_PAGE
    ): List<Repo>

}