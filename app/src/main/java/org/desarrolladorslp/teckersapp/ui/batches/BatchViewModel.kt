package org.desarrolladorslp.teckersapp.ui.batches

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.desarrolladorslp.teckersapp.model.Batch

class BatchViewModel : ViewModel() {
    var _batches = MutableLiveData<ArrayList<Batch>>().apply{
        value=getBatches()
    }

    fun getBatches() :ArrayList<Batch>
    {
        var batches :ArrayList<Batch> = arrayListOf()
        var batch1 = Batch("1","Batch","2020-11-12","2020-11-20","TareaNueva","")
        batches.add(batch1)
        var batch2 = Batch("2","Batch2","2020-11-12","2020-11-20","TareaNueva","")
        batches.add(batch2)
        var batch3 = Batch("3","Batch3","2020-11-12","2020-11-20","TareaNueva","")
        batches.add(batch3)
        return batches

    }
}