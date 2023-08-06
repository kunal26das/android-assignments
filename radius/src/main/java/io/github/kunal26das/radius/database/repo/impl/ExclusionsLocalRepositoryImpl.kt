package io.github.kunal26das.radius.database.repo.impl

import io.github.kunal26das.radius.database.dao.ExclusionDao
import io.github.kunal26das.radius.database.mapper.toExclusionEntities
import io.github.kunal26das.radius.database.repo.ExclusionsLocalRepository
import io.github.kunal26das.radius.domain.entity.FacilityOption
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