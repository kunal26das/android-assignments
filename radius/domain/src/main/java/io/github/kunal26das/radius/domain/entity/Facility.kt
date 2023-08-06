package io.github.kunal26das.radius.domain.entity

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
