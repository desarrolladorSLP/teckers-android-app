package org.desarrolladorslp.teckersapp.ui.deliverables

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.desarrolladorslp.teckersapp.exception.AuthorizationException
import org.desarrolladorslp.teckersapp.exception.ResponseException
import org.desarrolladorslp.teckersapp.service.APIEndpoint
import org.desarrolladorslp.teckersapp.service.DeliverableService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeliverableViewModel : ViewModel() {

    private var deliverablesService = APIEndpoint.instance()?.create(DeliverableService::class.java)
    val _responseException = MutableLiveData<ResponseException?>()
    val _authorizationException = MutableLiveData<AuthorizationException?>()
    var _deliverables = MutableLiveData<ArrayList<DeliverableHeader>>().apply{

        value =getDeliverables()
    }

    fun getDeliverables() :ArrayList<DeliverableHeader>{

        /*var deliverableCall = deliverablesService?.getDeliverables()
        deliverableCall?.enqueue(object : Callback<ArrayList<DeliverableHeader>> {
            override fun onResponse(call: Call<ArrayList<DeliverableHeader>>, response: Response<ArrayList<DeliverableHeader>>) {
                _deliverables.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<DeliverableHeader>>, t: Throwable) {
                val m = t.message;

                if (t is ResponseException) {
                    _responseException.value = t
                }else if(t is AuthorizationException)
                {
                    _authorizationException.value = t
                }
            }
        })*/

        val deliverable1 = DeliverableHeader("Photo grupo","10/08/2019","Accepted")
        val deliverable2 = DeliverableHeader("Photo grupoB","10/10/2019","Accepted")
        val deliverable3 = DeliverableHeader("Photo casa","21/10/2019","Blocked")
        val deliverable4 = DeliverableHeader("Tarea matematicas","02/11/2019","Accepted")
        val deliverable5 = DeliverableHeader("Enlace de video","07/11/2019","Accepted")
        val deliverable6 = DeliverableHeader("Documento Terminado","08/11/2019","Blocked")
        var deliverablesList = ArrayList<DeliverableHeader>();

        deliverablesList.add(deliverable1)
        deliverablesList.add(deliverable2)
        deliverablesList.add(deliverable3)
        deliverablesList.add(deliverable4)
        deliverablesList.add(deliverable5)
        deliverablesList.add(deliverable6)
        return deliverablesList

    }

}