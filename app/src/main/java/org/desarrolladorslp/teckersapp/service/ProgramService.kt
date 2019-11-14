package org.desarrolladorslp.teckersapp.service

import org.desarrolladorslp.teckersapp.model.Program
import retrofit2.Call
import retrofit2.http.GET

interface ProgramService {
    @GET("/api/program")
    fun getPrograms(): Call<ArrayList<Program>>
}
