package com.agentdesks.android.crm.assignment.feature.data.repo

import com.agentdesks.android.crm.assignment.feature.data.mapper.toFacility
import com.agentdesks.android.crm.assignment.feature.data.service.ApiService
import com.agentdesks.android.crm.assignment.feature.domain.entity.Facility
import com.agentdesks.android.crm.assignment.feature.domain.repo.FacilityRemoteRepository
import javax.inject.Inject

class FacilityRemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : FacilityRemoteRepository {
    override suspend fun getFacilities(): List<Facility> {
        val facilities = try {
            apiService.getFacilitiesAndExclusions().facilities ?: emptyList()
        } catch (e: Exception) {
            println(e.message)
            emptyList()
        }
        return facilities.map { it.toFacility }
    }
}