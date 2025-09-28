package io.github.kunal26das.geektrust.data.dto

import com.google.gson.annotations.SerializedName

data class VehicleDto constructor(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("total_no")
    val totalNo: Int? = null,
    @SerializedName("max_distance")
    val maxDistance: Int? = null,
    @SerializedName("speed")
    val speed: Int? = null,
)
