package org.desarrolladorslp.teckersapp.service

import org.desarrolladorslp.teckersapp.model.DeliverableHeader
import retrofit2.Call
import retrofit2.http.GET

interface DeliverableService {
    @GET("/api/tecker/deliverables")
    fun getDeliverables(): Call<ArrayList<DeliverableHeader>>
}