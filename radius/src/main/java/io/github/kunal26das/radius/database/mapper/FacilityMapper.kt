package io.github.kunal26das.radius.database.mapper

import io.github.kunal26das.radius.database.entity.FacilityEntity
import io.github.kunal26das.radius.domain.entity.Facility

internal val Facility.toFacility: FacilityEntity
    get() = FacilityEntity(
        id = id,
        name = name,
        options = options.map {
            it.toOption(id)
        }
    )

internal val FacilityEntity.toFacility: Facility
    get() = Facility(
        id = id,
        name = name,
        options = options.map {
            it.toOption(id)
        }
    )