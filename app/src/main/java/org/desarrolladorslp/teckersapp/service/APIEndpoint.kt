package org.desarrolladorslp.teckersapp.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIEndpoint {

    companion object {
        private val interceptor = TokenInterceptor()
        private var apiInstance: Retrofit? = null

        fun instance(): Retrofit? {
            if (apiInstance == null) {

                var client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

                apiInstance = Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://teckers-backend.herokuapp.com/")
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