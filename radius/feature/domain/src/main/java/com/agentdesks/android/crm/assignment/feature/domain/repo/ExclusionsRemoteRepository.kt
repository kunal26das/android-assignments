package com.agentdesks.android.crm.assignment.feature.domain.repo

import com.agentdesks.android.crm.assignment.feature.domain.entity.FacilityOption

interface ExclusionsRemoteRepository {
    suspend fun getExclusions(): Map<FacilityOption, List<FacilityOption>>
}