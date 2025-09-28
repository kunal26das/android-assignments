package io.github.kunal26das.geektrust.domain.repo

interface AuthRepository {
    suspend fun getToken(): String
}