package io.github.kunal26das.radius.data.mapper

import io.github.kunal26das.radius.data.entity.OptionDto
import io.github.kunal26das.radius.domain.entity.Option

fun OptionDto.toOption(
    facilityId: Int
): Option = Option(
    id = id?.toInt()!!,
    name = name.orEmpty(),
    icon = icon.orEmpty(),
    facilityId = facilityId,
)