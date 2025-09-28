package io.github.kunal26das.geektrust.domain.repo

import io.github.kunal26das.geektrust.domain.entity.Vehicle

interface VehicleRepository {
    suspend fun getVehicles(): List<Vehicle>
}