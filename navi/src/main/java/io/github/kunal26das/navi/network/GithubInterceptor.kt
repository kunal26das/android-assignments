package io.github.kunal26das.navi.network

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import io.github.kunal26das.navi.Constant
import okhttp3.Interceptor
import okhttp3.Response

class GithubInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder().apply {
            addHeader("Authorization", "Bearer ${Constant.token}")
            addHeader("Accept", "application/vnd.github+json")
        }.build())
    }
}