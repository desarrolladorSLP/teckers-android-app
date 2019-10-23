package org.desarrolladorslp.teckersapp.service

import org.desarrolladorslp.teckersapp.model.Tecker
import retrofit2.Call
import retrofit2.http.GET

interface ParentTeckersService {
    @GET("/api/parent/teckers")
    fun getTeckers(): Call<ArrayList<Tecker>>
}