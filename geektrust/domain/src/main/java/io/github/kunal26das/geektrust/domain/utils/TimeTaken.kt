package io.github.kunal26das.geektrust.domain.utils

import io.github.kunal26das.geektrust.domain.entity.Planet
import io.github.kunal26das.geektrust.domain.entity.Vehicle
import kotlin.math.min

fun calculateTimeTaken(
    planets: List<Planet>,
    vehicles: List<Vehicle>,
): Int {
    var time = 0
    repeat(min(planets.size, vehicles.size)) {
        time += planets[it].distance / vehicles[it].speed
    }
    return time
}

fun calculateTimeTaken(
    pairs: Collection<Pair<Planet, Vehicle?>>
) = calculateTimeTaken(
    planets = pairs.map { it.first },
    vehicles = pairs.mapNotNull { it.second },
)