package com.agentdesks.android.crm.assignment.database.repo

import com.agentdesks.android.crm.assignment.feature.domain.entity.FacilityOption

interface ExclusionsLocalRepository {
    suspend fun insertExclusions(map: Map<FacilityOption, List<FacilityOption>>)
    suspend fun getExclusions(): Map<FacilityOption, List<FacilityOption>>
    suspend fun hasExclusions(): Boolean
    suspend fun removeAllExclusions()
}