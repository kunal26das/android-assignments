package com.agentdesks.android.crm.assignment.feature.domain.usecase

import com.agentdesks.android.crm.assignment.feature.domain.entity.Facility

interface FacilityListUseCase {
    suspend fun getFacilities(): List<Facility>
}