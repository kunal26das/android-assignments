package io.github.kunal26das.geektrust.domain.usecase

import io.github.kunal26das.geektrust.domain.entity.Planet
import io.github.kunal26das.geektrust.domain.entity.Result
import io.github.kunal26das.geektrust.domain.entity.Vehicle
import io.github.kunal26das.geektrust.domain.repo.AuthRepository
import io.github.kunal26das.geektrust.domain.repo.FalconeRepository
import javax.inject.Inject

class FindFalconeUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val falconeRepository: FalconeRepository,
) {
    suspend fun findFalcone(
        planets: List<Planet>,
        vehicles: List<Vehicle>,
    ): Result {
        val token = authRepository.getToken()
        return falconeRepository.findFalcone(token, planets, vehicles)
    }
}