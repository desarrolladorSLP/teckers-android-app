package org.desarrolladorslp.teckersapp.ui.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.desarrolladorslp.teckersapp.model.Inbox
import org.desarrolladorslp.teckersapp.model.MessageHeader

class PageViewModel : ViewModel() {
    private var priorityIndex =1
    private lateinit var inbox: Inbox

    fun getmessages() :ArrayList<MessageHeader> {
        if(priorityIndex==2)
        {
            return inbox.lowPriority
        }
        return  inbox.highPriority
    }

    fun setInbox(inbox:Inbox)
    {
        this.inbox = inbox
    }
    fun setIndex(index: Int) {
        priorityIndex = index
    }
}