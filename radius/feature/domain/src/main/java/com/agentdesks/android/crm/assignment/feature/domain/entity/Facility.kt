package com.agentdesks.android.crm.assignment.feature.domain.entity


data class Facility constructor(
    val id: Int,
    val name: String = "",
    val options: List<Option> = emptyList(),
    val type: Type? = null,
) {
    enum class Type {
        PropertyType,
        NumberOfRooms,
        OtherFacilities,
    }
}
