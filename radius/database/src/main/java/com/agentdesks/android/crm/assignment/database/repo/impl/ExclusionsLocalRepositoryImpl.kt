package com.agentdesks.android.crm.assignment.database.repo.impl

import com.agentdesks.android.crm.assignment.database.dao.ExclusionDao
import com.agentdesks.android.crm.assignment.database.mapper.toExclusionEntities
import com.agentdesks.android.crm.assignment.database.repo.ExclusionsLocalRepository
import com.agentdesks.android.crm.assignment.feature.domain.entity.FacilityOption
import javax.inject.Inject

internal class ExclusionsLocalRepositoryImpl @Inject constructor(
    private val exclusionDao: ExclusionDao
) : ExclusionsLocalRepository {
    override suspend fun insertExclusions(map: Map<FacilityOption, List<FacilityOption>>) {
        exclusionDao.insertExclusions(map.flatMap {
            it.toExclusionEntities
        })
    }

    override suspend fun getExclusions(): Map<FacilityOption, List<FacilityOption>> {
        val map = mutableMapOf<FacilityOption, MutableList<FacilityOption>>()
        exclusionDao.getExclusions().forEach {
            val option = FacilityOption(it.facilityId1, it.optionId1)
            val antiOption = FacilityOption(it.facilityId2, it.optionId2)
            if (option in map) {
                map[option]?.add(antiOption)
            } else {
                map[option] = mutableListOf(antiOption)
            }
        }
        return map
    }

    override suspend fun hasExclusions(): Boolean {
        return exclusionDao.getExclusionsCount() > 0
    }

    override suspend fun removeAllExclusions() {
        exclusionDao.removeAllExclusion()
    }
}