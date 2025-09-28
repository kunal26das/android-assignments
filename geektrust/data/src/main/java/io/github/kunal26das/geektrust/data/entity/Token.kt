package io.github.kunal26das.geektrust.data.entity

import com.google.gson.annotations.SerializedName

data class Token constructor(
    @SerializedName("token")
    val token: String? = null
)
