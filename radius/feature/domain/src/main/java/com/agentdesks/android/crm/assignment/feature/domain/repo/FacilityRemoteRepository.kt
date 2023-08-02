package com.agentdesks.android.crm.assignment.feature.domain.repo

import com.agentdesks.android.crm.assignment.feature.domain.entity.Facility

interface FacilityRemoteRepository {
    suspend fun getFacilities(): List<Facility>
}