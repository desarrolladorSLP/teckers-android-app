package org.desarrolladorslp.teckersapp.service

import android.content.Context
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
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
        private lateinit var applicationLayout :ConstraintLayout

        fun setActivity(constraintLayout: ConstraintLayout)
        {
            applicationLayout = constraintLayout
        }

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
                                Snackbar.make(applicationLayout, "Error de Servidor", Snackbar.LENGTH_SHORT)
                                    .show()
                                return response
                            }
                            if(response.code() == 404)
                            {
                                Snackbar.make(applicationLayout, "No tienes permiso", Snackbar.LENGTH_SHORT)
                                    .show()
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

