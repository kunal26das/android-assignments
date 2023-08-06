package io.github.kunal26das.radius.database.repo

import io.github.kunal26das.radius.domain.entity.Facility

interface FacilityLocalRepository {
    suspend fun insertFacilities(facilities: List<Facility>)
    suspend fun getFacilities(): List<Facility>
    suspend fun hasFacilities(): Boolean
    suspend fun removeAllFacilities()
}