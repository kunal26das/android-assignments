package io.github.kunal26das.geektrust.data.entity

import com.google.gson.annotations.SerializedName

data class FindFalconeResponse constructor(
    @SerializedName("planet_name")
    val planetName: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("error")
    val error: String? = null,
)
