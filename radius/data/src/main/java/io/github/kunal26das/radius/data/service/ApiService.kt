package io.github.kunal26das.radius.data.service

import io.github.kunal26das.radius.data.entity.ApiResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/iranjith4/ad-assignment/db")
    suspend fun getFacilitiesAndExclusions(): ApiResponse
}