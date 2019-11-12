package org.desarrolladorslp.teckersapp.service

import org.desarrolladorslp.teckersapp.model.DeliverableHeader
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DeliverablesTeckerService {
    @GET("api/tecker/{teckerId}/deliverables")
    fun getDeliverables(@Path("teckerId") id:Int): Call<ArrayList<DeliverableHeader>>
}