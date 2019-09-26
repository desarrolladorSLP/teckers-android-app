package org.desarrolladorslp.teckersapp.service

import org.desarrolladorslp.teckersapp.model.Inbox
import retrofit2.Call

import retrofit2.http.GET

interface MessageService {

    @GET("/api/message")
    fun getMessages(): Call<Inbox>
}

