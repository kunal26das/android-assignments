package com.agentdesks.android.crm.assignment.database.repo

import com.agentdesks.android.crm.assignment.feature.domain.entity.Facility

interface FacilityLocalRepository {
    suspend fun insertFacilities(facilities: List<Facility>)
    suspend fun getFacilities(): List<Facility>
    suspend fun hasFacilities(): Boolean
    suspend fun removeAllFacilities()
}