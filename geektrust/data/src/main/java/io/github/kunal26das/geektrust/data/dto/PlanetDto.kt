package io.github.kunal26das.geektrust.data.dto

import com.google.gson.annotations.SerializedName

data class PlanetDto constructor(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("distance")
    val distance: Int? = null,
)