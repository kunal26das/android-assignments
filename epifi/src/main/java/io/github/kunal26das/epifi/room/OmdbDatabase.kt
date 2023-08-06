package io.github.kunal26das.epifi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.kunal26das.epifi.model.Element

@Database(
    version = 1,
    exportSchema = false,
    entities = [Element::class],
)
abstract class OmdbDatabase : RoomDatabase() {
    abstract val messageDao: OmdbDao
}