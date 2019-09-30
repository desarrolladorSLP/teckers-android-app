package org.desarrolladorslp.teckersapp.service

import okhttp3.OkHttpClient
import org.desarrolladorslp.teckersapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIEndpoint {

    companion object {
        private val interceptor = TokenInterceptor()
        private var apiInstance: Retrofit? = null
        private var baseUrl= BuildConfig.BaseUrl

        fun instance(): Retrofit? {
            if (apiInstance == null) {

                var client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

                apiInstance = Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return apiInstance;
        }

        fun setAccessToken(accessToken: String?) {
            interceptor.setAccessToken(accessToken)
        }
    }
}