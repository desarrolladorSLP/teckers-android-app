package org.desarrolladorslp.teckersapp.service

import okhttp3.Interceptor
import okhttp3.Response
import org.desarrolladorslp.teckersapp.exception.AuthorizationException
import org.desarrolladorslp.teckersapp.exception.ResponseException
import java.io.IOException

class ResponseHandlerInterceptor : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var response = chain.proceed(request)

        when(response.code())
        {
            200 -> return response
            401 -> throw AuthorizationException(response.message())
        }

        throw ResponseException(response.message())
    }
}