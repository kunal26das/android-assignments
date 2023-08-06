package io.github.kunal26das.radius.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class OptionEntity constructor(
    @PrimaryKey
    @ColumnInfo("id")
    var id: Int,
    @ColumnInfo("name")
    var name: String,
    @ColumnInfo("icon")
    var icon: String,
    @ColumnInfo("facility_id")
    var facilityId: Int,
) {
    constructor() : this(0, "", "", 0)
}
