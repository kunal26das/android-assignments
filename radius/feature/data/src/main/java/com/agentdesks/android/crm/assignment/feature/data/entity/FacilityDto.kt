package com.agentdesks.android.crm.assignment.feature.data.entity

import com.google.gson.annotations.SerializedName

data class FacilityDto constructor(
    @SerializedName("facility_id")
    val facilityId: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("options")
    val options: List<OptionDto>? = null,
)
