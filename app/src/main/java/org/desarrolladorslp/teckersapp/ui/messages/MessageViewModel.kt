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

        val inbox: Inbox = getInbox().value!!.data!!

        fun getInbox() = NetworkCall<Inbox>().makeCall(messageService!!.getMessages())

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