package io.github.kunal26das.epifi.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun getOmdbDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, OmdbDatabase::class.java,
        context.javaClass.simpleName
    ).build()

    @Provides
    fun getElementDao(
        omdbDatabase: OmdbDatabase
    ) = omdbDatabase.messageDao
}