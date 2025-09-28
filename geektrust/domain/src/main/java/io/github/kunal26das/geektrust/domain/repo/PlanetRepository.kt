package io.github.kunal26das.geektrust.domain.repo

import io.github.kunal26das.geektrust.domain.entity.Planets

interface PlanetRepository {
    suspend fun getPlanets(): Planets
}