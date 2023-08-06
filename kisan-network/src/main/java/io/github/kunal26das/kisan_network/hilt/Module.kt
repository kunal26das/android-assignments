package io.github.kunal26das.kisan_network.hilt

import android.content.Context
import android.telephony.SmsManager
import androidx.room.Room
import dagger.Module
import io.github.kunal26das.kisan_network.room.MessageDao
import io.github.kunal26das.kisan_network.room.MessageDatabase
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    fun getSmsManager(): SmsManager {
        return SmsManager.getDefault()!!
    }

    @Provides
    fun getMessageDatabase(
        @ApplicationContext context: Context
    ): MessageDatabase {
        return Room.databaseBuilder(
            context,
            MessageDatabase::class.java,
            MessageDatabase::class.java.simpleName,
        ).build()
    }

    @Provides
    fun getMessageDao(
        messageDatabase: MessageDatabase,
    ): MessageDao {
        return messageDatabase.messageDao
    }
}