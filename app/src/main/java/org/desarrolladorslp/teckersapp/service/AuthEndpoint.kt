package org.desarrolladorslp.teckersapp.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthEndpoint {

    companion object {
        private var authInstance: Retrofit? = null

        fun instance(): Retrofit? {

            if (authInstance == null) {

                val client = OkHttpClient.Builder()
                    .addInterceptor(AppCredentialsInterceptor())
                    .build()

                authInstance = Retrofit.Builder()
                    .baseUrl("https://teckers-backend.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }

            return authInstance
        }

    }
}

