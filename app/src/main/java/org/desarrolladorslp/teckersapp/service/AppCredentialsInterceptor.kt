package org.desarrolladorslp.teckersapp.service

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response
import org.desarrolladorslp.teckersapp.BuildConfig


open class AppCredentialsInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val appCredentials  = BuildConfig.user +":"+ BuildConfig.password
        val charset = Charsets.UTF_8
        val byteArray = appCredentials.toByteArray(charset)
        val base64 = Base64.encodeToString(byteArray, Base64.DEFAULT)
        val value = base64.replaceFirst("\n", "")
        var request = chain.request()
        val headers =
            request.headers().newBuilder()
                .add("Authorization", "Basic $value").build()

        request = request.newBuilder().headers(headers).build()

        return chain.proceed(request)

    }
}