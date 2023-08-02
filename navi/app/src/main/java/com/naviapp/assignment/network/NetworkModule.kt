package com.naviapp.assignment.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.naviapp.assignment.BuildConfig
import com.naviapp.assignment.Constant
import com.naviapp.assignment.service.PullService
import com.naviapp.assignment.service.RepoService
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

    private fun getGson(): Gson {
        return GsonBuilder().create()
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(GithubInterceptor())
        }.build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.BASE_URL)
            client(getOkHttpClient())
            addConverterFactory(GsonConverterFactory.create(getGson()))
        }.build()
    }

    @Provides
    fun getRepoService(): RepoService {
        return getRetrofit().create(RepoService::class.java)
    }

    @Provides
    fun getPullService(): PullService {
        return getRetrofit().create(PullService::class.java)
    }

}