package io.github.kunal26das.radius.data.di

import io.github.kunal26das.radius.data.repo.ExclusionsRemoteRepositoryImpl
import io.github.kunal26das.radius.data.repo.FacilityRemoteRepositoryImpl
import io.github.kunal26das.radius.data.service.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.kunal26das.radius.domain.repo.ExclusionsRemoteRepository
import io.github.kunal26das.radius.domain.repo.FacilityRemoteRepository
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