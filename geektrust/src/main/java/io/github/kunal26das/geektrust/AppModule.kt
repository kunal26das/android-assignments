package io.github.kunal26das.geektrust

import com.google.gson.Gson
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.BASE_URL)
            client(OkHttpClient.Builder().apply {
                addInterceptor { chain ->
                    val request = chain.request().newBuilder().apply {
                        addHeader("Accept", "application/json")
                    }.build()
                    chain.proceed(request)
                }
            }.build())
            addConverterFactory(GsonConverterFactory.create(Gson()))
            addCallAdapterFactory(ResultCallAdapterFactory.create())
        }.build()
    }
}