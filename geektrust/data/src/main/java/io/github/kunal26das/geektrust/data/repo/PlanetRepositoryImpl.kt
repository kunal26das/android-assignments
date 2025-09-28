package io.github.kunal26das.geektrust.data.repo

import io.github.kunal26das.geektrust.data.mapper.toPlanets
import io.github.kunal26das.geektrust.data.service.PlanetService
import io.github.kunal26das.geektrust.domain.entity.Planet
import io.github.kunal26das.geektrust.domain.entity.Planets
import io.github.kunal26das.geektrust.domain.repo.PlanetRepository
import java.lang.Integer.min
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val planetService: PlanetService
) : PlanetRepository {
    override suspend fun getPlanets(): Planets {
        val result = planetService.getPlanets()
        if (result.isFailure) {
            println(result.exceptionOrNull()?.message)
        }
        val planetsDto = result.getOrNull()
        val planets = when (planetsDto?.size) {
            null, 0 -> localPlanets
            else -> planetsDto.toPlanets
        }
        return Planets(
            list = planets,
            searchLimit = min(PLANETS_SEARCH_LIMIT, planets.size)
        )
    }

    private val localPlanets: List<Planet>
        get() = mutableListOf<Planet>().apply {
            add(Planet(name = "Donlon", distance = 100))
            add(Planet(name = "Enchai", distance = 200))
            add(Planet(name = "Jebing", distance = 300))
            add(Planet(name = "Sapir", distance = 400))
            add(Planet(name = "Lerbin", distance = 500))
            add(Planet(name = "Pingasor", distance = 500))
        }

    companion object {
        private const val PLANETS_SEARCH_LIMIT = 4
    }
}