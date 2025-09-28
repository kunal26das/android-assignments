package io.github.kunal26das.geektrust.data.mapper

import io.github.kunal26das.geektrust.data.dto.PlanetDto
import io.github.kunal26das.geektrust.domain.entity.Planet

val PlanetDto.toPlanet: Planet
    get() = Planet(
        name = name.orEmpty(),
        distance = distance ?: 0,
    )

val List<PlanetDto>?.toPlanets: List<Planet>
    get() = this?.map { it.toPlanet } ?: emptyList()