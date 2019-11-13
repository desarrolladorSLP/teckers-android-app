package org.desarrolladorslp.teckersapp.service

import org.desarrolladorslp.teckersapp.model.Batch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProgramBatchesService {
    @GET("/api/batch/program/{programId}")
    fun getProgramBatches(@Path("programId") id:String): Call<ArrayList<Batch>>
}