package io.github.kunal26das.radius.usecase

import io.github.kunal26das.radius.database.repo.ExclusionsLocalRepository
import io.github.kunal26das.radius.domain.entity.FacilityOption
import io.github.kunal26das.radius.domain.repo.ExclusionsRemoteRepository
import io.github.kunal26das.radius.domain.usecase.ExclusionMapUseCase
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