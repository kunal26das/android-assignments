package io.github.kunal26das.epifi.service

import io.github.kunal26das.epifi.Constant
import io.github.kunal26das.epifi.model.Element
import io.github.kunal26das.epifi.model.Response
import io.github.kunal26das.epifi.network.NetworkModule.DEFAULT_FORMAT
import io.github.kunal26das.epifi.network.NetworkModule.FIRST_PAGE
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbService {

    @GET("/")
    suspend fun getList(
        @Query("type") type: String? = null,
        @Query("page") page: Int = FIRST_PAGE,
        @Query("s") searchQuery: String? = null,
        @Query("r") format: String = DEFAULT_FORMAT,
        @Query("apikey") apiKey: String = Constant.apiKey,
    ): Response

    @GET("/")
    suspend fun getElement(
        @Query("i") imdbId: String? = null,
        @Query("r") format: String = DEFAULT_FORMAT,
        @Query("apikey") apiKey: String = Constant.apiKey,
    ): Element
}