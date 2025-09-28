package io.github.kunal26das.geektrust.data.mapper

import io.github.kunal26das.geektrust.data.dto.VehicleDto
import io.github.kunal26das.geektrust.domain.entity.Vehicle

fun VehicleDto.getToVehicle(
    index: Int
) = Vehicle(
    id = index,
    name = name.orEmpty(),
    maxDistance = maxDistance ?: 0,
    speed = speed ?: 0,
)

val List<VehicleDto>?.toVehicles: List<Vehicle>
    get() = this?.flatMap { vehicleDto ->
        val vehicles = mutableListOf<Vehicle>()
        repeat(vehicleDto.totalNo ?: 0) {
            vehicles.add(vehicleDto.getToVehicle(it))
        }
        vehicles
    } ?: emptyList()