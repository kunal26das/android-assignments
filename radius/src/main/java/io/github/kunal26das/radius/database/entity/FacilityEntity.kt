package io.github.kunal26das.radius.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
internal data class FacilityEntity constructor(
    @PrimaryKey
    @ColumnInfo("id")
    var id: Int,
    @ColumnInfo("name")
    var name: String,
    @Ignore val options: List<OptionEntity>,
) {
    constructor() : this(0, "", emptyList())
}
