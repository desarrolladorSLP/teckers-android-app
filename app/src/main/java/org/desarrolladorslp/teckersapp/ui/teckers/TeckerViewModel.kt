package org.desarrolladorslp.teckersapp.ui.teckers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.desarrolladorslp.teckersapp.model.Tecker

class TeckerViewModel : ViewModel() {
    var _teckers = MutableLiveData<ArrayList<Tecker>>()

    fun getParentTeckers() {

        val tecker1 = Tecker("24","https://cdn2.glamour.es/uploads/images/thumbs/es/glam/4/s/2017/15/zac_efron_7917_620x698.jpg","Juan")
        val tecker2 = Tecker("25","https://cdn2.glamour.es/uploads/images/thumbs/es/glam/4/s/2017/15/zac_efron_7917_620x698.jpg","Pedro")

        var teckers = ArrayList<Tecker>()

        teckers.add(tecker1)
        teckers.add(tecker2)

        _teckers.value=teckers
    }
}