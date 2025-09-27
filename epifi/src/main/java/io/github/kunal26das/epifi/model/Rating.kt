package io.github.kunal26das.epifi.model


import com.google.gson.annotations.SerializedName


data class Rating(
    @SerializedName("Source")
    val source: String? = null,

    @SerializedName("Value")
    val value: String? = null
)
