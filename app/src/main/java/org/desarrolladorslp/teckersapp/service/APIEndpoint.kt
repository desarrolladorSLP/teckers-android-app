package org.desarrolladorslp.teckersapp.service

import androidx.core.content.ContextCompat.startActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.desarrolladorslp.teckersapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class APIEndpoint {

    companion object {
        private var interceptor = TokenInterceptor()
        private var apiInstance: Retrofit? = null
        private var baseUrl= BuildConfig.BaseUrl

        fun instance(): Retrofit? {
            if (apiInstance == null) {

                var client = OkHttpClient.Builder()
                    .addInterceptor(object : TokenInterceptor() {
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