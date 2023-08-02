package com.agentdesks.android.crm.assignment.di

import com.agentdesks.android.crm.assignment.BuildConfig
import com.agentdesks.android.crm.assignment.database.di.DatabaseModule
import com.facebook.stetho.okhttp3.StethoInterceptor
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
abstract class NetworkModule {
    companion object {
        @Provides
        fun providesGson(): Gson {
            return GsonBuilder().create()
        }

        @Provides
        fun providesOhHttpClient(): OkHttpClient {
            return OkHttpClient.Builder().apply {
                addNetworkInterceptor(StethoInterceptor())
            }.build()
        }

        @Provides
        fun providesRetrofit(
            okHttpClient: OkHttpClient,
            gson: Gson
        ): Retrofit {
            return Retrofit.Builder().apply {
                baseUrl(BuildConfig.BASE_URL)
                client(okHttpClient)
                addConverterFactory(GsonConverterFactory.create(gson))
            }.build()
        }
    }
}