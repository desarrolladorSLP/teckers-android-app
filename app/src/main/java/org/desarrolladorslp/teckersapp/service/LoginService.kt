package org.desarrolladorslp.teckersapp.service

import org.desarrolladorslp.teckersapp.model.LoggedUser
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @FormUrlEncoded
    @POST("/oauth/token")
    fun login(@Field("grant_type") granType: String, @Field("firebase_token_id") firebaseTokenId: String?): Call<LoggedUser>
}