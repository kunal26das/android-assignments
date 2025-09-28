package io.github.kunal26das.geektrust.data.repo

import io.github.kunal26das.geektrust.data.service.AuthService
import io.github.kunal26das.geektrust.domain.repo.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun getToken(): String {
        return authService.getToken().getOrNull()?.token.orEmpty()
    }
}