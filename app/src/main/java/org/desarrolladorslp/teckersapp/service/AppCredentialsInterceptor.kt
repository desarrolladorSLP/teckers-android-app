package org.desarrolladorslp.teckersapp.service

import okhttp3.Interceptor
import okhttp3.Response

class AppCredentialsInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        val headers =
            request.headers().newBuilder()
                .add("Authorization", "Basic QW5kcm9pZEFwcDp2bGV3MzV4OQ==").build()

        request = request.newBuilder().headers(headers).build()

        return chain.proceed(request)

    }
}