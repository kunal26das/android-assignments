package com.agentdesks.android.crm.assignment.database.mapper

import com.agentdesks.android.crm.assignment.database.entity.OptionEntity
import com.agentdesks.android.crm.assignment.feature.domain.entity.Option

internal fun Option.toOption(
    facilityId: Int
): OptionEntity {
    return OptionEntity(
        id = id,
        name = name,
        icon = icon,
        facilityId = facilityId,
    )
}

internal fun OptionEntity.toOption(
    facilityId: Int
): Option {
    return Option(
        id = id,
        name = name,
        icon = icon,
        facilityId = facilityId,
    )
}