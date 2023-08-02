package com.epifi.assignment.network

import com.epifi.assignment.BuildConfig
import com.epifi.assignment.service.OmdbService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    const val PAGE_SIZE = 10
    const val FIRST_PAGE = 1
    const val DEFAULT_FORMAT = "json"

    private fun getGson(): Gson {
        return GsonBuilder().create()
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.BASE_URL)
            client(getOkHttpClient())
            addConverterFactory(GsonConverterFactory.create(getGson()))
        }.build()
    }

    @Provides
    fun getOmdbService(): OmdbService {
        return getRetrofit().create(OmdbService::class.java)
    }

}