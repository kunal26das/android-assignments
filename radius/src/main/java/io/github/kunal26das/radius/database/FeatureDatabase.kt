package io.github.kunal26das.radius.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.kunal26das.radius.database.dao.ExclusionDao
import io.github.kunal26das.radius.database.dao.FacilityDao
import io.github.kunal26das.radius.database.dao.OptionDao
import io.github.kunal26das.radius.database.entity.ExclusionEntity
import io.github.kunal26das.radius.database.entity.FacilityEntity
import io.github.kunal26das.radius.database.entity.OptionEntity

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