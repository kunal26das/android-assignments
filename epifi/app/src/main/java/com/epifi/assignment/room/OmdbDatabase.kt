package com.epifi.assignment.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.epifi.assignment.model.Element

@Database(
    version = 1,
    exportSchema = false,
    entities = [Element::class],
)
abstract class OmdbDatabase : RoomDatabase() {

    abstract val messageDao: OmdbDao

}