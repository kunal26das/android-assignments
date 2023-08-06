package io.github.kunal26das.epifi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    @SerializedName("Source")
    val source: String? = null,

    @SerializedName("Value")
    val value: String? = null
) : Parcelable
