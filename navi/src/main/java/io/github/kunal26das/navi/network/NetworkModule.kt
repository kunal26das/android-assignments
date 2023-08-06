package io.github.kunal26das.navi.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.github.kunal26das.navi.service.PullService
import io.github.kunal26das.navi.service.RepoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.kunal26das.navi.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @NaviGson
    fun getGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @NaviOkHttpClient
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(GithubInterceptor())
            addNetworkInterceptor(StethoInterceptor())
        }.build()
    }

    @Provides
    @NaviRetrofit
    fun getRetrofit(
        @NaviOkHttpClient okHttpClient: OkHttpClient,
        @NaviGson gson: Gson,
    ): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(Constant.BASE_URL)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create(gson))
        }.build()
    }

    @Provides
    fun getRepoService(
        @NaviRetrofit retrofit: Retrofit
    ): RepoService {
        return retrofit.create(RepoService::class.java)
    }

    @Provides
    fun getPullService(
        @NaviRetrofit retrofit: Retrofit
    ): PullService {
        return retrofit.create(PullService::class.java)
    }
}