package io.github.kunal26das.radius.domain.usecase

import io.github.kunal26das.radius.domain.entity.FacilityOption

interface ExclusionMapUseCase {
    suspend fun getExclusions(): Map<FacilityOption, List<FacilityOption>>
}