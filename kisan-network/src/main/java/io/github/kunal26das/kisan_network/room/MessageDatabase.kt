package io.github.kunal26das.kisan_network.room

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.kunal26das.kisan_network.message.Message

@Database(
    version = 1,
    exportSchema = false,
    entities = [Message::class],
)
abstract class MessageDatabase : RoomDatabase() {
    abstract val messageDao: MessageDao
}