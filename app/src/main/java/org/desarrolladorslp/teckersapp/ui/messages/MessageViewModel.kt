package org.desarrolladorslp.teckersapp.ui.messages

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.desarrolladorslp.teckersapp.model.Inbox
import org.desarrolladorslp.teckersapp.model.MessageHeader
import org.desarrolladorslp.teckersapp.service.APIEndpoint
import org.desarrolladorslp.teckersapp.service.MessageService
import org.desarrolladorslp.teckersapp.service.NetworkCall


class MessageViewModel : ViewModel() {

        private var messageService = APIEndpoint.instance()?.create(MessageService::class.java);
        private val _inbox = MutableLiveData<Inbox>().apply {

            value=generateInbox().value!!.data!!

        }
        val inbox: Inbox = _inbox.value!!

        fun generateInbox() = NetworkCall<Inbox>().makeCall(messageService!!.getMessages())

        fun totalMessages() : ArrayList<MessageHeader>
        {
            val inbox = inbox
            for (lowMessage in inbox.lowPriority )
            {
                inbox.highPriority.add(lowMessage)
            }
            return inbox.highPriority
        }



}