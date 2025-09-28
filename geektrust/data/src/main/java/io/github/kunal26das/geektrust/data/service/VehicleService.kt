package io.github.kunal26das.geektrust.data.service

import io.github.kunal26das.geektrust.data.dto.VehicleDto
import retrofit2.http.GET

interface VehicleService {
    @GET("/vehicles")
    suspend fun getVehicles(): Result<List<VehicleDto>>
}