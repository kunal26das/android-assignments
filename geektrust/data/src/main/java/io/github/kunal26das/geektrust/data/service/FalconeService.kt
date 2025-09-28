package io.github.kunal26das.geektrust.data.service

import io.github.kunal26das.geektrust.data.entity.FindFalconeRequest
import io.github.kunal26das.geektrust.data.entity.FindFalconeResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface FalconeService {
    @POST("/find")
    suspend fun findFalcone(@Body body: FindFalconeRequest): Result<FindFalconeResponse>
}