package io.github.kunal26das.radius.domain.repo

import io.github.kunal26das.radius.domain.entity.Facility

interface FacilityRemoteRepository {
    suspend fun getFacilities(): List<Facility>
}