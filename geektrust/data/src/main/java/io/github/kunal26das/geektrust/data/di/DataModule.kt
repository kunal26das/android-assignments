package io.github.kunal26das.geektrust.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.kunal26das.geektrust.data.repo.AuthRepositoryImpl
import io.github.kunal26das.geektrust.data.repo.FalconeRepositoryImpl
import io.github.kunal26das.geektrust.data.repo.PlanetRepositoryImpl
import io.github.kunal26das.geektrust.data.repo.VehicleRepositoryImpl
import io.github.kunal26das.geektrust.data.service.AuthService
import io.github.kunal26das.geektrust.data.service.FalconeService
import io.github.kunal26das.geektrust.data.service.PlanetService
import io.github.kunal26das.geektrust.data.service.VehicleService
import io.github.kunal26das.geektrust.domain.repo.AuthRepository
import io.github.kunal26das.geektrust.domain.repo.FalconeRepository
import io.github.kunal26das.geektrust.domain.repo.PlanetRepository
import io.github.kunal26das.geektrust.domain.repo.VehicleRepository
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindFalconeRepository(
        falconeRepositoryImpl: FalconeRepositoryImpl
    ): FalconeRepository

    @Binds
    abstract fun bindPlanetRepository(
        planetRepositoryImpl: PlanetRepositoryImpl
    ): PlanetRepository

    @Binds
    abstract fun bindVehicleRepository(
        vehicleRepositoryImpl: VehicleRepositoryImpl
    ): VehicleRepository

    companion object NetworkModule {
        @Provides
        fun provideAuthService(retrofit: Retrofit): AuthService {
            return retrofit.create(AuthService::class.java)
        }

        @Provides
        fun provideFalconeService(retrofit: Retrofit): FalconeService {
            return retrofit.create(FalconeService::class.java)
        }

        @Provides
        fun providePlanetService(retrofit: Retrofit): PlanetService {
            return retrofit.create(PlanetService::class.java)
        }

        @Provides
        fun provideVehicleService(retrofit: Retrofit): VehicleService {
            return retrofit.create(VehicleService::class.java)
        }
    }
}