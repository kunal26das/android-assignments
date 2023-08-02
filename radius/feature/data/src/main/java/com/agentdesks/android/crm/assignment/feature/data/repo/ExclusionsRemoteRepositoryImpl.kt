package com.agentdesks.android.crm.assignment.feature.data.repo

import com.agentdesks.android.crm.assignment.feature.data.mapper.toFacilityOption
import com.agentdesks.android.crm.assignment.feature.data.service.ApiService
import com.agentdesks.android.crm.assignment.feature.domain.entity.FacilityOption
import com.agentdesks.android.crm.assignment.feature.domain.repo.ExclusionsRemoteRepository
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