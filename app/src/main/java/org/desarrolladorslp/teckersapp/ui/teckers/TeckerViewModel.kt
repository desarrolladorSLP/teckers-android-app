package org.desarrolladorslp.teckersapp.ui.teckers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.desarrolladorslp.teckersapp.exception.AuthorizationException
import org.desarrolladorslp.teckersapp.exception.ResponseException
import org.desarrolladorslp.teckersapp.model.Tecker
import org.desarrolladorslp.teckersapp.service.APIEndpoint
import org.desarrolladorslp.teckersapp.service.BatchTeckersService
import org.desarrolladorslp.teckersapp.service.MentorTeckersService
import org.desarrolladorslp.teckersapp.service.ParentTeckersService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeckerViewModel : ViewModel() {
    var _teckers = MutableLiveData<ArrayList<Tecker>>()
    var parentTeckersService = APIEndpoint.instance().create(ParentTeckersService::class.java)
    var mentorTeckersService = APIEndpoint.instance().create(MentorTeckersService::class.java)
    var batchTeckersService = APIEndpoint.instance().create(BatchTeckersService::class.java)
    val _responseException = MutableLiveData<ResponseException?>()
    val _authorizationException = MutableLiveData<AuthorizationException?>()
    val selectedTecker = MutableLiveData<Tecker>()

    fun getTeckersList(call : Call<ArrayList<Tecker>>?)
    {
        call?.enqueue(object : Callback<ArrayList<Tecker>> {
            override fun onResponse(call: Call<ArrayList<Tecker>>, response: Response<ArrayList<Tecker>>) {
                _teckers.value = response.body()
            }
            override fun onFailure(call: Call<ArrayList<Tecker>>, t: Throwable) {
                val m = t.message

                if (t is ResponseException) {
                    _responseException.value = t
                }else if(t is AuthorizationException)
                {
                    _authorizationException.value = t
                }
            }
        })
    }

    fun getParentTeckers() {
        val parentTeckersCall = parentTeckersService.getTeckers()
        getTeckersList(parentTeckersCall)
    }
    fun getMentorTeckers() {
        val mentorTeckersCall = mentorTeckersService.getTeckers()
        getTeckersList(mentorTeckersCall)
    }

    fun getBatchTeckers(batchId:String) {
        val batchTeckersCall = batchTeckersService.getTeckers(batchId)
        getTeckersList(batchTeckersCall)
    }
    
    fun getSelectedTecker():Tecker
    {
        return selectedTecker.value!!
    }

    fun setSelectedTecker(tecker: Tecker) {
        selectedTecker.postValue(tecker)
    }
}