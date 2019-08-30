package org.desarrolladorslp.teckersapp.ui.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TeamViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is teams Fragment"
    }
    val text: LiveData<String> = _text
}