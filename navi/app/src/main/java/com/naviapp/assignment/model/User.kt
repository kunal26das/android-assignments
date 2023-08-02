package com.naviapp.assignment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("login")
    val name: String? = null,

    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
) : Parcelable