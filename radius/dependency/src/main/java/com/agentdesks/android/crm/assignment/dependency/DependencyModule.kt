package com.agentdesks.android.crm.assignment.dependency

import com.agentdesks.android.crm.assignment.feature.data.di.FeatureDataModule
import com.agentdesks.android.crm.assignment.database.di.DatabaseModule
import com.agentdesks.android.crm.assignment.feature.di.FeatureModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        DatabaseModule::class,
        FeatureModule::class,
        FeatureDataModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
object DependencyModule