package io.github.kunal26das.navi.model


import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("login")
    val name: String? = null,

    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
)