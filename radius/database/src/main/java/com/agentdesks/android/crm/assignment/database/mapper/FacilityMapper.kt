package com.agentdesks.android.crm.assignment.database.mapper

import com.agentdesks.android.crm.assignment.database.entity.FacilityEntity
import com.agentdesks.android.crm.assignment.feature.domain.entity.Facility

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