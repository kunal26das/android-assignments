package io.github.kunal26das.radius.di

import io.github.kunal26das.radius.usecase.ExclusionMapUseCaseImpl
import io.github.kunal26das.radius.usecase.FacilityListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.kunal26das.radius.domain.usecase.ExclusionMapUseCase
import io.github.kunal26das.radius.domain.usecase.FacilityListUseCase

@Module(
    includes = [
        DatabaseModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
abstract class RadiusModule {
    @Binds
    internal abstract fun bindsFacilityListUseCase(
        facilityListUseCase: FacilityListUseCaseImpl
    ): FacilityListUseCase

    @Binds
    internal abstract fun bindsExclusionMapUseCase(
        exclusionMapUseCaseImpl: ExclusionMapUseCaseImpl
    ): ExclusionMapUseCase
}