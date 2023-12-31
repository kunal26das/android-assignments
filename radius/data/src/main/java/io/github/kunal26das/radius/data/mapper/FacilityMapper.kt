package io.github.kunal26das.radius.data.mapper

import io.github.kunal26das.radius.data.entity.FacilityDto
import io.github.kunal26das.radius.domain.entity.Facility

private const val FACILITY_PROPERTY_TYPE = "1"
private const val FACILITY_NUMBER_OF_ROOMS = "2"
private const val FACILITY_OTHER_FACILITIES = "3"

val FacilityDto.toFacility: Facility
    get() = Facility(
        id = facilityId?.toInt()!!,
        name = name.orEmpty(),
        options = options?.map {
            it.toOption(facilityId.toInt())
        } ?: emptyList(),
        type = when (facilityId) {
            FACILITY_PROPERTY_TYPE -> Facility.Type.PropertyType
            FACILITY_NUMBER_OF_ROOMS -> Facility.Type.NumberOfRooms
            FACILITY_OTHER_FACILITIES -> Facility.Type.OtherFacilities
            else -> null
        }
    )