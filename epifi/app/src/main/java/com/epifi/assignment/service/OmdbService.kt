package com.epifi.assignment.service

import com.epifi.assignment.BuildConfig
import com.epifi.assignment.model.Element
import com.epifi.assignment.model.Response
import com.epifi.assignment.network.NetworkModule.DEFAULT_FORMAT
import com.epifi.assignment.network.NetworkModule.FIRST_PAGE
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbService {

    @GET("/")
    suspend fun getList(
        @Query("type") type: String? = null,
        @Query("page") page: Int = FIRST_PAGE,
        @Query("s") searchQuery: String? = null,
        @Query("r") format: String = DEFAULT_FORMAT,
        @Query("apikey") apiKey: String = BuildConfig.API_KEY,
    ): Response

    @GET("/")
    suspend fun getElement(
        @Query("i") imdbId: String? = null,
        @Query("r") format: String = DEFAULT_FORMAT,
        @Query("apikey") apiKey: String = BuildConfig.API_KEY,
    ): Element

}