package org.desarrolladorslp.teckersapp.ui.batches

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.desarrolladorslp.teckersapp.exception.AuthorizationException
import org.desarrolladorslp.teckersapp.exception.ResponseException
import org.desarrolladorslp.teckersapp.model.Batch
import org.desarrolladorslp.teckersapp.service.APIEndpoint
import org.desarrolladorslp.teckersapp.service.ProgramBatchesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BatchViewModel : ViewModel() {

    private var programBatchesService = APIEndpoint.instance().create(ProgramBatchesService::class.java)
    var _batches = MutableLiveData<ArrayList<Batch>>()
    val _responseException = MutableLiveData<ResponseException?>()
    val _authorizationException = MutableLiveData<AuthorizationException?>()
    var _batchSelected = MutableLiveData<Batch>()

    fun setBatchSelected(batch:Batch)
    {
        _batchSelected.value =batch
    }

    fun getProgramBatches(programId:String)
    {
        var programBatchesCall = programBatchesService?.getProgramBatches(programId)
        programBatchesCall?.enqueue(object : Callback<ArrayList<Batch>> {
            override fun onResponse(call: Call<ArrayList<Batch>>, response: Response<ArrayList<Batch>>) {
                _batches.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<Batch>>, t: Throwable) {
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