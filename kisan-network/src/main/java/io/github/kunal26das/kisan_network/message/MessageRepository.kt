package io.github.kunal26das.kisan_network.message

import io.github.kunal26das.kisan_network.room.MessageDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessageRepository @Inject constructor(
    private val messageDao: MessageDao
) {

    fun getMessageList(): Flow<List<Message>> {
        return messageDao.getMessageList()
    }

    suspend fun insertMessage(message: Message) {
        messageDao.insertMessage(message)
    }
}