package com.agentdesks.android.crm.assignment.feature.usecase

import com.agentdesks.android.crm.assignment.database.repo.ExclusionsLocalRepository
import com.agentdesks.android.crm.assignment.feature.domain.entity.FacilityOption
import com.agentdesks.android.crm.assignment.feature.domain.repo.ExclusionsRemoteRepository
import com.agentdesks.android.crm.assignment.feature.domain.usecase.ExclusionMapUseCase
import javax.inject.Inject

class ExclusionMapUseCaseImpl @Inject constructor(
    private val exclusionsLocalRepository: ExclusionsLocalRepository,
    private val exclusionsRemoteRepository: ExclusionsRemoteRepository
) : ExclusionMapUseCase {
    override suspend fun getExclusions(): Map<FacilityOption, List<FacilityOption>> {
        return if (exclusionsLocalRepository.hasExclusions()) {
            exclusionsLocalRepository.getExclusions()
        } else {
            exclusionsRemoteRepository.getExclusions().also {
                exclusionsLocalRepository.insertExclusions(it)
            }
        }
    }
}