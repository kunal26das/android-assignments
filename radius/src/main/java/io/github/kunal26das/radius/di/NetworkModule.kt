package io.github.kunal26das.radius.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.kunal26das.radius.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {
    companion object {
        @Provides
        @RadiusGson
        fun providesGson(): Gson {
            return GsonBuilder().create()
        }

        @Provides
        @RadiusOkHttpClient
        fun providesOhHttpClient(): OkHttpClient {
            return OkHttpClient.Builder().apply {
                addNetworkInterceptor(StethoInterceptor())
            }.build()
        }

        @Provides
        fun providesRetrofit(
            @RadiusOkHttpClient okHttpClient: OkHttpClient,
            @RadiusGson gson: Gson
        ): Retrofit {
            return Retrofit.Builder().apply {
                baseUrl(Constant.BASE_URL)
                client(okHttpClient)
                addConverterFactory(GsonConverterFactory.create(gson))
            }.build()
        }
    }
}