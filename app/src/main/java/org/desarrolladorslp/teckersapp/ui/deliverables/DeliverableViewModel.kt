package org.desarrolladorslp.teckersapp.ui.deliverables

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.desarrolladorslp.teckersapp.exception.AuthorizationException
import org.desarrolladorslp.teckersapp.exception.ResponseException
import org.desarrolladorslp.teckersapp.model.DeliverableHeader
import org.desarrolladorslp.teckersapp.service.APIEndpoint
import org.desarrolladorslp.teckersapp.service.DeliverableService
import org.desarrolladorslp.teckersapp.service.DeliverablesTeckerService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeliverableViewModel : ViewModel() {

    private var deliverablesService = APIEndpoint.instance().create(DeliverableService::class.java)
    private var deliverablesTeckerService = APIEndpoint.instance().create(DeliverablesTeckerService::class.java)
    val _responseException = MutableLiveData<ResponseException?>()
    val _authorizationException = MutableLiveData<AuthorizationException?>()
    var _deliverables = MutableLiveData<ArrayList<DeliverableHeader>>()

    fun getDleiverablesList(call: Call<ArrayList<DeliverableHeader>>)
    {
        call.enqueue(object : Callback<ArrayList<DeliverableHeader>> {
            override fun onResponse(call: Call<ArrayList<DeliverableHeader>>, response: Response<ArrayList<DeliverableHeader>>) {
                _deliverables.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<DeliverableHeader>>, t: Throwable) {
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
    fun getDeliverables(){

        val deliverableCall = deliverablesService.getDeliverables()
        getDleiverablesList(deliverableCall)

    }
    fun getTeckerDeliverables(teckerId:String)
    {
        val deliverableCall = deliverablesTeckerService.getDeliverables(teckerId)
        getDleiverablesList(deliverableCall)
    }

}