package com.agentdesks.android.crm.assignment.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agentdesks.android.crm.assignment.database.entity.ExclusionEntity

@Dao
internal interface ExclusionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExclusions(exclusions: List<ExclusionEntity>)

    @Query("SELECT * FROM ExclusionEntity")
    suspend fun getExclusions(): List<ExclusionEntity>

    @Query("SELECT COUNT(*) FROM ExclusionEntity")
    suspend fun getExclusionsCount(): Int

    @Query("DELETE FROM ExclusionEntity")
    suspend fun removeAllExclusion()
}