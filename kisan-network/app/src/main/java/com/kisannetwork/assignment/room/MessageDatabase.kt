package com.kisannetwork.assignment.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kisannetwork.assignment.message.Message

@Database(
    version = 1,
    exportSchema = false,
    entities = [Message::class],
)
abstract class MessageDatabase : RoomDatabase() {

    abstract val messageDao: MessageDao

}