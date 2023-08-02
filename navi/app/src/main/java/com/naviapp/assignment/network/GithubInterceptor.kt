package com.naviapp.assignment.network

import com.naviapp.assignment.BuildConfig
import com.naviapp.assignment.Constant
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

class GithubInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder().apply {
            addHeader("Authorization", "Bearer ${BuildConfig.TOKEN}")
            addHeader("Accept", "application/vnd.github+json")
        }.build())
    }
}