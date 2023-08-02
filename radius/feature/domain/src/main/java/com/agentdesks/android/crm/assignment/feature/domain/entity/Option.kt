package com.agentdesks.android.crm.assignment.feature.domain.entity

data class Option constructor(
    val id: Int,
    val name: String = "",
    val icon: String = "",
    val facilityId: Int,
) {
    val ofFacility
        get() = FacilityOption(
            facilityId = facilityId,
            optionId = id,
        )
}
