package io.github.kunal26das.radius.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.kunal26das.radius.database.entity.OptionEntity

@Dao
internal interface OptionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOptions(options: List<OptionEntity>)

    @Query("SELECT * FROM OptionEntity WHERE facility_id == :facilityId")
    suspend fun getOptions(facilityId: Int): List<OptionEntity>

    @Query("DELETE FROM OptionEntity")
    suspend fun removeAllOptions()
}