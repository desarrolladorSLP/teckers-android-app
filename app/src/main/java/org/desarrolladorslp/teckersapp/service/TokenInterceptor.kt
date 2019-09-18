package org.desarrolladorslp.teckersapp.service

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor() : Interceptor {
    private var accessToken: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var headers =
            request.headers().newBuilder().add("Authorization", "Bearer " + accessToken).build();

        request = request.newBuilder().headers(headers).build();

        return chain.proceed(request);

    }

    fun setAccessToken(accessToken: String?) {
        this.accessToken = accessToken;
    }
}