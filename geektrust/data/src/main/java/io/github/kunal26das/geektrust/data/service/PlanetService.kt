package io.github.kunal26das.geektrust.data.service

import io.github.kunal26das.geektrust.data.dto.PlanetDto
import retrofit2.http.GET

interface PlanetService {
    @GET("/planets")
    suspend fun getPlanets(): Result<List<PlanetDto>>
}