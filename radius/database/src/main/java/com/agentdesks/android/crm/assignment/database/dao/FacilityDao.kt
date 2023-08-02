package com.agentdesks.android.crm.assignment.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agentdesks.android.crm.assignment.database.entity.FacilityEntity

@Dao
internal interface FacilityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFacilities(facilities: List<FacilityEntity>)

    @Query("SELECT * FROM FacilityEntity")
    suspend fun getFacilities(): List<FacilityEntity>

    @Query("SELECT COUNT(*) FROM FacilityEntity")
    suspend fun getFacilitiesCount(): Int

    @Query("DELETE FROM FacilityEntity")
    suspend fun removeAllFacilities()
}