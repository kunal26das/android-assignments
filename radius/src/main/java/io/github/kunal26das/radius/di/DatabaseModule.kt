package io.github.kunal26das.radius.di

import android.content.Context
import androidx.room.Room
import io.github.kunal26das.radius.database.FeatureDatabase
import io.github.kunal26das.radius.database.dao.ExclusionDao
import io.github.kunal26das.radius.database.dao.FacilityDao
import io.github.kunal26das.radius.database.dao.OptionDao
import io.github.kunal26das.radius.database.repo.ExclusionsLocalRepository
import io.github.kunal26das.radius.database.repo.impl.FacilityLocalRepositoryImpl
import io.github.kunal26das.radius.database.repo.FacilityLocalRepository
import io.github.kunal26das.radius.database.repo.impl.ExclusionsLocalRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {
    @Binds
    internal abstract fun bindsFacilityRepository(
        facilityRepository: FacilityLocalRepositoryImpl
    ): FacilityLocalRepository

    @Binds
    internal abstract fun bindsExclusionRepository(
        exclusionRepository: ExclusionsLocalRepositoryImpl
    ): ExclusionsLocalRepository

    companion object {
        @Provides
        internal fun providesFeatureDatabase(
            @ApplicationContext context: Context
        ): FeatureDatabase {
            return Room.databaseBuilder(
                context,
                FeatureDatabase::class.java,
                context.packageName
            ).build()
        }

        @Provides
        internal fun providesExclusionDao(
            featureDatabase: FeatureDatabase
        ): ExclusionDao {
            return featureDatabase.exclusionDao
        }

        @Provides
        internal fun providesFacilityDao(
            featureDatabase: FeatureDatabase
        ): FacilityDao {
            return featureDatabase.facilityDao
        }

        @Provides
        internal fun providesOptionDao(
            featureDatabase: FeatureDatabase
        ): OptionDao {
            return featureDatabase.optionDao
        }
    }
}