package io.github.kunal26das.epifi.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import io.github.kunal26das.epifi.service.OmdbService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.kunal26das.epifi.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    const val PAGE_SIZE = 10
    const val FIRST_PAGE = 1
    const val DEFAULT_FORMAT = "json"

    @Provides
    @EpifiGson
    fun getGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @EpifiOkHttpClient
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addNetworkInterceptor(StethoInterceptor())
        }.build()
    }

    @Provides
    @EpifiRetrofit
    fun getRetrofit(
        @EpifiOkHttpClient okHttpClient: OkHttpClient,
        @EpifiGson gson: Gson,
    ): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(Constant.BASE_URL)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create(gson))
        }.build()
    }

    @Provides
    fun getOmdbService(
        @EpifiRetrofit retrofit: Retrofit
    ): OmdbService {
        return retrofit.create(OmdbService::class.java)
    }
}