package com.zrifapps.core.interceptor

import com.zrifapps.core.config.ConfigProvider
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val configProvider: ConfigProvider) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("key", configProvider.apiKey)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()


        return chain.proceed(newRequest)
    }
}
