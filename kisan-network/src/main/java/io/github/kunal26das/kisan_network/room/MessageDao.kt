package io.github.kunal26das.kisan_network.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.kunal26das.kisan_network.message.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Message)

    @Query("SELECT * FROM Message ORDER BY timestamp DESC")
    fun getMessageList(): Flow<List<Message>>
}