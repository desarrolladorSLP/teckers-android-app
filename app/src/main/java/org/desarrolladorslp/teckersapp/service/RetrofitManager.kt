package org.desarrolladorslp.teckersapp.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.desarrolladorslp.teckersapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RetrofitManager {

    companion object {
        private var authInstance: Retrofit? = null
        private var baseUrl= BuildConfig.BaseUrl
        fun instance(): Retrofit? {

            if (authInstance == null) {

                val client = OkHttpClient.Builder()
                    .addInterceptor(object : AppCredentialsInterceptor() {
                        @Throws(IOException::class)
                        override fun intercept(chain: Interceptor.Chain): Response {
                            var request = chain.request()
                            var response = chain.proceed(request)
                            if(response.code() == 500)
                            {
                                //muestra el toast en el activity
                                return response
                            }
                            return response
                        }
                    })
                    .build()

                authInstance = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }

            return authInstance
        }

    }
}

