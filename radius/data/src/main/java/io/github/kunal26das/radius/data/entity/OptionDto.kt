package io.github.kunal26das.radius.data.entity

import com.google.gson.annotations.SerializedName

data class OptionDto constructor(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("icon")
    val icon: String? = null,
    @SerializedName("id")
    val id: String? = null,
)
