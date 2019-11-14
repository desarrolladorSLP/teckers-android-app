package org.desarrolladorslp.teckersapp.service

import org.desarrolladorslp.teckersapp.model.Tecker
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BatchTeckersService {
    @GET("/api/batch/{batchId}/teckers")
    fun getDeliverables(@Path("batchId") id:Int): Call<ArrayList<Tecker>>
}