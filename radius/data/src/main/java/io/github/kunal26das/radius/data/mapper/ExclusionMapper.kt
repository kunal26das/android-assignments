package io.github.kunal26das.radius.data.mapper

import io.github.kunal26das.radius.data.entity.ExclusionDto
import io.github.kunal26das.radius.domain.entity.FacilityOption

val ExclusionDto.toFacilityOption: FacilityOption
    get() = FacilityOption(
        facilityId = facilityId?.toInt()!!,
        optionId = optionsId?.toInt()!!,
    )