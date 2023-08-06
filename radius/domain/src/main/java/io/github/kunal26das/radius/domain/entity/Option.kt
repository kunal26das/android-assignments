package io.github.kunal26das.radius.domain.entity

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
