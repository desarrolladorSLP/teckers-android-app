package org.desarrolladorslp.teckersapp.service

import org.desarrolladorslp.teckersapp.model.Inbox
import org.desarrolladorslp.teckersapp.model.MessageHeader
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface MessageService {

    @FormUrlEncoded
    @GET("/api/messages")
    fun getMessages(): Call<Inbox>
}

