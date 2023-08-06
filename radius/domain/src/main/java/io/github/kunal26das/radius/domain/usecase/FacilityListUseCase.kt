package io.github.kunal26das.radius.domain.usecase

import io.github.kunal26das.radius.domain.entity.Facility

interface FacilityListUseCase {
    suspend fun getFacilities(): List<Facility>
}