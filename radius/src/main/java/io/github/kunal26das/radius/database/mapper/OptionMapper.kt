package io.github.kunal26das.radius.database.mapper

import io.github.kunal26das.radius.database.entity.OptionEntity
import io.github.kunal26das.radius.domain.entity.Option

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