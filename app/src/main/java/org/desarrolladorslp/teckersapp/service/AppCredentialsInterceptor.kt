package org.desarrolladorslp.teckersapp.service

import okhttp3.Interceptor
import okhttp3.Response
import org.desarrolladorslp.teckersapp.BuildConfig

class AppCredentialsInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val appCredentials : String = BuildConfig.user +":"+ BuildConfig.password
        open class Base64
        val decode: ByteArray = android.util.Base64.decode(appCredentials,0)
        var request = chain.request()
        val headers =
            request.headers().newBuilder()
                .add("Authorization", "Basic QW5kcm9pZEFwcDp2bGV3MzV4OQ==").build()

        request = request.newBuilder().headers(headers).build()

        return chain.proceed(request)

    }
}