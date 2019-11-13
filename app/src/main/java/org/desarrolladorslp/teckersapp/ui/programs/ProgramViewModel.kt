package org.desarrolladorslp.teckersapp.ui.programs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.desarrolladorslp.teckersapp.exception.AuthorizationException
import org.desarrolladorslp.teckersapp.exception.ResponseException
import org.desarrolladorslp.teckersapp.model.Program
import org.desarrolladorslp.teckersapp.service.APIEndpoint
import org.desarrolladorslp.teckersapp.service.ProgramService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProgramViewModel : ViewModel() {

    private var programService = APIEndpoint.instance().create(ProgramService::class.java)
    var _programs = MutableLiveData<ArrayList<Program>>()
    val _responseException = MutableLiveData<ResponseException?>()
    val _authorizationException = MutableLiveData<AuthorizationException?>()

    fun getPrograms(){
        var programCall = programService?.getPrograms()
        programCall?.enqueue(object : Callback<ArrayList<Program>> {
            override fun onResponse(call: Call<ArrayList<Program>>, response: Response<ArrayList<Program>>) {
                _programs.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<Program>>, t: Throwable) {
                if (t is ResponseException) {
                    _responseException.value = t
                }else if(t is AuthorizationException)
                {
                    _authorizationException.value = t
                }
            }
        })


    }
}