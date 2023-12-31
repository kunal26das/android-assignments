package io.github.kunal26das.radius.database.mapper

import io.github.kunal26das.radius.database.entity.ExclusionEntity
import io.github.kunal26das.radius.domain.entity.FacilityOption

internal val Map.Entry<FacilityOption, List<FacilityOption>>.toExclusionEntities: List<ExclusionEntity>
    get() {
        val facilityId1 = key.facilityId
        val optionId1 = key.optionId
        return value.map {
            val facilityId2 = it.facilityId
            val optionId2 = it.optionId
            ExclusionEntity(
                facilityId1 = facilityId1,
                optionId1 = optionId1,
                facilityId2 = facilityId2,
                optionId2 = optionId2
            )
        }
    }