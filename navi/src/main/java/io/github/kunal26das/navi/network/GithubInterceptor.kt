package io.github.kunal26das.navi.network

import io.github.kunal26das.navi.Constant
import okhttp3.Interceptor
import okhttp3.Response

class GithubInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder().apply {
            val token = Constant.token
            if (token.isNotBlank()) {
                addHeader("Authorization", "Bearer $token")
            }
            addHeader("Accept", "application/vnd.github+json")
        }.build())
    }
}