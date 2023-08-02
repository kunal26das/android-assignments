package com.epifi.assignment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Response(
    @SerializedName("Search")
    val search: List<Element>? = null,

    @SerializedName("totalResults")
    private val _totalResults: String? = null,

    @SerializedName("Response")
    private val _response: String? = null,
) : Parcelable {

    val totalResults
        get() = _totalResults?.toIntOrNull()

    val response
        get() = _response?.toBoolean()

}
