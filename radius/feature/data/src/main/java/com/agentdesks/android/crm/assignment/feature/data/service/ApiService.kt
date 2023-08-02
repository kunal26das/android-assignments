package com.agentdesks.android.crm.assignment.feature.data.service

import com.agentdesks.android.crm.assignment.feature.data.entity.ApiResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/iranjith4/ad-assignment/db")
    suspend fun getFacilitiesAndExclusions(): ApiResponse
}