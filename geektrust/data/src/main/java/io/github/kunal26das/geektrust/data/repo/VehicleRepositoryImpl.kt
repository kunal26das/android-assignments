package io.github.kunal26das.geektrust.data.repo

import io.github.kunal26das.geektrust.data.mapper.toVehicles
import io.github.kunal26das.geektrust.data.service.VehicleService
import io.github.kunal26das.geektrust.domain.entity.Vehicle
import io.github.kunal26das.geektrust.domain.repo.VehicleRepository
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val vehicleService: VehicleService,
) : VehicleRepository {
    override suspend fun getVehicles(): List<Vehicle> {
        val result = vehicleService.getVehicles()
        if (result.isFailure) {
            println(result.exceptionOrNull()?.message)
        }
        val vehicles = result.getOrNull()
        return when (vehicles?.size) {
            null, 0 -> localVehicles
            else -> vehicles.toVehicles
        }
    }

    private val localVehicles: List<Vehicle>
        get() = mutableListOf<Vehicle>().apply {
            add(Vehicle(id = 1, name = "Space pod", maxDistance = 200, speed = 2))
            add(Vehicle(id = 2, name = "Space pod", maxDistance = 200, speed = 2))
            add(Vehicle(id = 1, name = "Space rocket", maxDistance = 300, speed = 4))
            add(Vehicle(id = 1, name = "Space shuttle", maxDistance = 400, speed = 5))
            add(Vehicle(id = 1, name = "Space ship", maxDistance = 600, speed = 10))
            add(Vehicle(id = 2, name = "Space ship", maxDistance = 600, speed = 10))
        }
}