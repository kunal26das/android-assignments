package com.agentdesks.android.crm.assignment.feature.usecase

import com.agentdesks.android.crm.assignment.database.repo.FacilityLocalRepository
import com.agentdesks.android.crm.assignment.feature.domain.entity.Facility
import com.agentdesks.android.crm.assignment.feature.domain.repo.FacilityRemoteRepository
import com.agentdesks.android.crm.assignment.feature.domain.usecase.FacilityListUseCase
import javax.inject.Inject

internal class FacilityListUseCaseImpl @Inject constructor(
    private val facilityRemoteRepository: FacilityRemoteRepository,
    private val facilityLocalRepository: FacilityLocalRepository,
) : FacilityListUseCase {
    override suspend fun getFacilities(): List<Facility> {
        return if (facilityLocalRepository.hasFacilities()) {
            facilityLocalRepository.getFacilities()
        } else {
            facilityRemoteRepository.getFacilities().also {
                facilityLocalRepository.insertFacilities(it)
            }
        }
    }
}