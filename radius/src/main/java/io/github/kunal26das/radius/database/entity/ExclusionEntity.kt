package io.github.kunal26das.radius.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    primaryKeys = [
        "facility_id_1",
        "option_id_1",
        "facility_id_2",
        "option_id_2",
    ]
)
internal data class ExclusionEntity constructor(
    @ColumnInfo("facility_id_1")
    val facilityId1: Int,
    @ColumnInfo("option_id_1")
    val optionId1: Int,
    @ColumnInfo("facility_id_2")
    val facilityId2: Int,
    @ColumnInfo("option_id_2")
    val optionId2: Int,
)
