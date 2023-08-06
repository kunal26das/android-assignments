package io.github.kunal26das.radius.database.repo

import io.github.kunal26das.radius.domain.entity.FacilityOption

interface ExclusionsLocalRepository {
    suspend fun insertExclusions(map: Map<FacilityOption, List<FacilityOption>>)
    suspend fun getExclusions(): Map<FacilityOption, List<FacilityOption>>
    suspend fun hasExclusions(): Boolean
    suspend fun removeAllExclusions()
}