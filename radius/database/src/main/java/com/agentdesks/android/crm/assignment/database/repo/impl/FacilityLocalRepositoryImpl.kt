package com.agentdesks.android.crm.assignment.database.repo.impl

import com.agentdesks.android.crm.assignment.database.dao.FacilityDao
import com.agentdesks.android.crm.assignment.database.dao.OptionDao
import com.agentdesks.android.crm.assignment.database.mapper.toFacility
import com.agentdesks.android.crm.assignment.database.mapper.toOption
import com.agentdesks.android.crm.assignment.database.repo.FacilityLocalRepository
import com.agentdesks.android.crm.assignment.feature.domain.entity.Facility
import javax.inject.Inject

internal class FacilityLocalRepositoryImpl @Inject constructor(
    private val facilityDao: FacilityDao,
    private val optionDao: OptionDao,
) : FacilityLocalRepository {
    override suspend fun insertFacilities(facilities: List<Facility>) {
        facilityDao.insertFacilities(facilities.map { facility ->
            optionDao.insertOptions(facility.options.map {  option ->
                option.toOption(facilityId = facility.id)
            })
            facility.toFacility
        })
    }

    override suspend fun getFacilities(): List<Facility> {
        return facilityDao.getFacilities().map { facility ->
            facility.toFacility.copy(options = optionDao.getOptions(facility.id).map {
                it.toOption(facility.id)
            })
        }
    }

    override suspend fun hasFacilities(): Boolean {
        return facilityDao.getFacilitiesCount() > 0
    }

    override suspend fun removeAllFacilities() {
        facilityDao.removeAllFacilities()
        optionDao.removeAllOptions()
    }
}