package com.kisannetwork.assignment.hilt

import android.content.Context
import android.telephony.SmsManager
import androidx.room.Room
import com.kisannetwork.assignment.room.MessageDao
import com.kisannetwork.assignment.room.MessageDatabase
import dagger.Module
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

    private fun getMessageDatabase(
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
        @ApplicationContext context: Context,
    ): MessageDao {
        return getMessageDatabase(context).messageDao
    }

}