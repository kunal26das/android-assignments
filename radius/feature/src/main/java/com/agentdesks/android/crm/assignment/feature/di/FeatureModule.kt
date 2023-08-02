package com.agentdesks.android.crm.assignment.feature.di

import com.agentdesks.android.crm.assignment.feature.domain.usecase.ExclusionMapUseCase
import com.agentdesks.android.crm.assignment.feature.domain.usecase.FacilityListUseCase
import com.agentdesks.android.crm.assignment.feature.usecase.ExclusionMapUseCaseImpl
import com.agentdesks.android.crm.assignment.feature.usecase.FacilityListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureModule {
    @Binds
    internal abstract fun bindsFacilityListUseCase(
        facilityListUseCase: FacilityListUseCaseImpl
    ): FacilityListUseCase

    @Binds
    internal abstract fun bindsExclusionMapUseCase(
        exclusionMapUseCaseImpl: ExclusionMapUseCaseImpl
    ): ExclusionMapUseCase
}