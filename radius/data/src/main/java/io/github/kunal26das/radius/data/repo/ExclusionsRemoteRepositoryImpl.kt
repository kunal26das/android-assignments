package io.github.kunal26das.radius.data.repo

import io.github.kunal26das.radius.data.service.ApiService
import io.github.kunal26das.radius.data.mapper.toFacilityOption
import io.github.kunal26das.radius.domain.entity.FacilityOption
import io.github.kunal26das.radius.domain.repo.ExclusionsRemoteRepository
import javax.inject.Inject

class ExclusionsRemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ExclusionsRemoteRepository {
    override suspend fun getExclusions(): Map<FacilityOption, List<FacilityOption>> {
        val map = mutableMapOf<FacilityOption, MutableList<FacilityOption>>()
        try {
            apiService.getFacilitiesAndExclusions().exclusions?.filter {
                it.size == 2
            }?.forEach {
                val option = it[0].toFacilityOption
                val antiOption = it[1].toFacilityOption
                map.add(option, antiOption)
                map.add(antiOption, option)
            }
        } catch (e: Exception) {
            println(e.message)
        }
        return map
    }

    private fun MutableMap<FacilityOption, MutableList<FacilityOption>>.add(
        option: FacilityOption,
        antiOption: FacilityOption
    ) {
        if (option in this) {
            this[option]?.add(antiOption)
        } else {
            this[option] = mutableListOf(antiOption)
        }
    }
}