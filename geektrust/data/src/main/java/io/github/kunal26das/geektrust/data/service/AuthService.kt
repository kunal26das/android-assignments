package io.github.kunal26das.geektrust.data.service

import io.github.kunal26das.geektrust.data.entity.Token
import retrofit2.http.POST

interface AuthService {
    @POST("/token")
    suspend fun getToken(): Result<Token>
}