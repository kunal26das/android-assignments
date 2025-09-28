package io.github.kunal26das.geektrust.data.repo

import io.github.kunal26das.geektrust.data.entity.FindFalconeRequest
import io.github.kunal26das.geektrust.data.service.FalconeService
import io.github.kunal26das.geektrust.domain.entity.Planet
import io.github.kunal26das.geektrust.domain.entity.Result
import io.github.kunal26das.geektrust.domain.entity.Vehicle
import io.github.kunal26das.geektrust.domain.repo.FalconeRepository
import io.github.kunal26das.geektrust.domain.utils.calculateTimeTaken
import javax.inject.Inject

class FalconeRepositoryImpl @Inject constructor(
    private val falconeService: FalconeService,
) : FalconeRepository {
    override suspend fun findFalcone(
        token: String,
        planets: List<Planet>,
        vehicles: List<Vehicle>,
    ): Result {
        val result = falconeService.findFalcone(
            FindFalconeRequest(
                token = token,
                planetNames = planets.map { it.name },
                vehicleNames = vehicles.map { it.name },
            )
        )
        val response = result.getOrNull()
        return when {
            response?.status == "success" -> when (val planet = planets.find {
                it.name == response.planetName
            }) {
                null -> Result.Unknown
                else -> Result.Success(
                    planet = planet,
                    timeTaken = calculateTimeTaken(planets, vehicles)
                )
            }

            response?.status == "false" -> Result.Failure
            response?.error.isNullOrEmpty().not() -> {
                Result.Error(message = response?.error.orEmpty())
            }

            result.isFailure -> {
                Result.Error(message = result.exceptionOrNull()?.message.orEmpty())
            }

            else -> Result.Unknown
        }
    }
}