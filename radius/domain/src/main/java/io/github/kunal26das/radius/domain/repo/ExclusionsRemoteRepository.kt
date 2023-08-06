package io.github.kunal26das.radius.domain.repo

import io.github.kunal26das.radius.domain.entity.FacilityOption

interface ExclusionsRemoteRepository {
    suspend fun getExclusions(): Map<FacilityOption, List<FacilityOption>>
}