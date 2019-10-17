package org.desarrolladorslp.teckersapp.service

import org.desarrolladorslp.teckersapp.model.DeliverableHeader
import retrofit2.Call
import retrofit2.http.GET

interface DeliverableService {
    @GET("/api/deliverable")
    fun getDeliverables(): Call<ArrayList<DeliverableHeader>>
}