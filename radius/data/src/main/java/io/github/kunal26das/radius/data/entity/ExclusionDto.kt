package io.github.kunal26das.radius.data.entity

import com.google.gson.annotations.SerializedName

data class ExclusionDto constructor(
    @SerializedName("facility_id")
    val facilityId: String? = null,
    @SerializedName("options_id")
    val optionsId: String? = null,
)
