package org.desarrolladorslp.teckersapp.service

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.snackbar.Snackbar
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
        private lateinit var applicationLayout : ConstraintLayout

        fun setActivity(constraintLayout: ConstraintLayout)
        {
            applicationLayout = constraintLayout
        }
        fun instance(): Retrofit? {
            if (apiInstance == null) {

                var client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(ResponseHandlerInterceptor())
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