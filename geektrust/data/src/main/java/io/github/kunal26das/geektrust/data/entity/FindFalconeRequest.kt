package io.github.kunal26das.geektrust.data.entity

import com.google.gson.annotations.SerializedName

data class FindFalconeRequest constructor(
    @SerializedName("token")
    val token: String,
    @SerializedName("planet_names")
    val planetNames: List<String>,
    @SerializedName("vehicle_names")
    val vehicleNames: List<String>,
)
