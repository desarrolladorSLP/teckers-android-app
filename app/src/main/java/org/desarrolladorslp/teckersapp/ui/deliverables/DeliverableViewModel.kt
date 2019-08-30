package org.desarrolladorslp.teckersapp.ui.deliverables

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DeliverableViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is deliverables Fragment"
    }
    val text: LiveData<String> = _text
}