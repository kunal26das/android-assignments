package io.github.kunal26das.radius.database.repo.impl

import io.github.kunal26das.radius.database.dao.FacilityDao
import io.github.kunal26das.radius.database.dao.OptionDao
import io.github.kunal26das.radius.database.mapper.toFacility
import io.github.kunal26das.radius.database.mapper.toOption
import io.github.kunal26das.radius.database.repo.FacilityLocalRepository
import io.github.kunal26das.radius.domain.entity.Facility
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