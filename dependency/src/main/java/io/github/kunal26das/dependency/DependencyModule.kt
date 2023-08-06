package io.github.kunal26das.dependency

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.kunal26das.radius.data.di.FeatureDataModule

@Module(
    includes = [
        FeatureDataModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
object DependencyModule