package com.agentdesks.android.crm.assignment.feature.domain.usecase

import com.agentdesks.android.crm.assignment.feature.domain.entity.FacilityOption

interface ExclusionMapUseCase {
    suspend fun getExclusions(): Map<FacilityOption, List<FacilityOption>>
}