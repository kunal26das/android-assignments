package io.github.kunal26das.geektrust.domain.repo

import io.github.kunal26das.geektrust.domain.entity.Planet
import io.github.kunal26das.geektrust.domain.entity.Result
import io.github.kunal26das.geektrust.domain.entity.Vehicle

interface FalconeRepository {
    suspend fun findFalcone(
        token: String,
        planets: List<Planet>,
        vehicles: List<Vehicle>,
    ): Result
}