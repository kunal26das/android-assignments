package io.github.kunal26das.dependency

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.kunal26das.radius.data.di.RadiusDataModule

@Module(
    includes = [
        RadiusDataModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
object DependencyModule