package com.agentdesks.android.crm.assignment.feature.data.mapper

import com.agentdesks.android.crm.assignment.feature.data.entity.OptionDto
import com.agentdesks.android.crm.assignment.feature.domain.entity.Option

fun OptionDto.toOption(
    facilityId: Int
): Option = Option(
    id = id?.toInt()!!,
    name = name.orEmpty(),
    icon = icon.orEmpty(),
    facilityId = facilityId,
)