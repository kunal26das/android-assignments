package com.agentdesks.android.crm.assignment.feature.data.mapper

import com.agentdesks.android.crm.assignment.feature.data.entity.ExclusionDto
import com.agentdesks.android.crm.assignment.feature.domain.entity.FacilityOption

val ExclusionDto.toFacilityOption: FacilityOption
    get() = FacilityOption(
        facilityId = facilityId?.toInt()!!,
        optionId = optionsId?.toInt()!!,
    )