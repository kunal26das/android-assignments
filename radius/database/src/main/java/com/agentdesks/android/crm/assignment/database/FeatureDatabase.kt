package com.agentdesks.android.crm.assignment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agentdesks.android.crm.assignment.database.dao.ExclusionDao
import com.agentdesks.android.crm.assignment.database.dao.FacilityDao
import com.agentdesks.android.crm.assignment.database.dao.OptionDao
import com.agentdesks.android.crm.assignment.database.entity.ExclusionEntity
import com.agentdesks.android.crm.assignment.database.entity.FacilityEntity
import com.agentdesks.android.crm.assignment.database.entity.OptionEntity

@Database(
    entities = [
        ExclusionEntity::class,
        FacilityEntity::class,
        OptionEntity::class,
    ],
    version = FeatureDatabase.VERSION
)
internal abstract class FeatureDatabase : RoomDatabase() {
    abstract val exclusionDao: ExclusionDao
    abstract val facilityDao: FacilityDao
    abstract val optionDao: OptionDao
    companion object {
        internal const val VERSION = 1
    }
}