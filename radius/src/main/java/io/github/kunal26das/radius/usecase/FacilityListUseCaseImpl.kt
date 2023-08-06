package io.github.kunal26das.radius.usecase

import io.github.kunal26das.radius.domain.entity.Facility
import io.github.kunal26das.radius.database.repo.FacilityLocalRepository
import io.github.kunal26das.radius.domain.repo.FacilityRemoteRepository
import io.github.kunal26das.radius.domain.usecase.FacilityListUseCase
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