package com.agentdesks.android.crm.assignment.feature.data.entity

import com.google.gson.annotations.SerializedName

data class ApiResponse constructor(
    @SerializedName("facilities")
    val facilities: List<FacilityDto>? = null,
    @SerializedName("exclusions")
    val exclusions: List<List<ExclusionDto>>? = null,
)
