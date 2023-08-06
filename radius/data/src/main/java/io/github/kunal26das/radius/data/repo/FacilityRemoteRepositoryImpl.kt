package io.github.kunal26das.radius.data.repo

import io.github.kunal26das.radius.data.service.ApiService
import io.github.kunal26das.radius.data.mapper.toFacility
import io.github.kunal26das.radius.domain.entity.Facility
import io.github.kunal26das.radius.domain.repo.FacilityRemoteRepository
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