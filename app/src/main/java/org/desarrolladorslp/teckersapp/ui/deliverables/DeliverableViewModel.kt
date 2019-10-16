package org.desarrolladorslp.teckersapp.ui.deliverables

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DeliverableViewModel : ViewModel() {

    var _deliverables = MutableLiveData<ArrayList<DeliverableHeader>>().apply{

        value =createDeliverables()
    }

    fun createDeliverables() :ArrayList<DeliverableHeader>{

        val deliverable1 = DeliverableHeader("Photo grupo","10/08/2019","Accepted")
        val deliverable2 = DeliverableHeader("Photo grupoB","10/10/2019","Accepted")
        val deliverable3 = DeliverableHeader("Photo casa","21/10/2019","Accepted")
        val deliverable4 = DeliverableHeader("Tarea matematicas","02/11/2019","Accepted")
        val deliverable5 = DeliverableHeader("Enlace de video","07/11/2019","Accepted")
        val deliverable6 = DeliverableHeader("Documento Terminado","08/11/2019","Accepted")
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