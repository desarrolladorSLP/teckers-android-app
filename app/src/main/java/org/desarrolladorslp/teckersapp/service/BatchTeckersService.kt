package org.desarrolladorslp.teckersapp.service

import org.desarrolladorslp.teckersapp.model.Tecker
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BatchTeckersService {
    @GET("/api/batch/{batchId}/teckers")
    fun getTeckers(@Path("batchId") id:String): Call<ArrayList<Tecker>>
}