package com.agentdesks.android.crm.assignment.feature.data.di

import com.agentdesks.android.crm.assignment.feature.data.repo.ExclusionsRemoteRepositoryImpl
import com.agentdesks.android.crm.assignment.feature.data.repo.FacilityRemoteRepositoryImpl
import com.agentdesks.android.crm.assignment.feature.data.service.ApiService
import com.agentdesks.android.crm.assignment.feature.domain.repo.ExclusionsRemoteRepository
import com.agentdesks.android.crm.assignment.feature.domain.repo.FacilityRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureDataModule {
    @Binds
    abstract fun bindsExclusionRepository(
        exclusionsRepository: ExclusionsRemoteRepositoryImpl
    ) : ExclusionsRemoteRepository

    @Binds
    abstract fun bindsFacilityRepository(
        facilityRepository: FacilityRemoteRepositoryImpl
    ) : FacilityRemoteRepository

    companion object {
        @Provides
        fun providesApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }
}