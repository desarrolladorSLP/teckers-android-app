package org.desarrolladorslp.teckersapp.ui.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.desarrolladorslp.teckersapp.model.Inbox
import org.desarrolladorslp.teckersapp.model.MessageHeader

class MessageViewModel : ViewModel() {

    private val _messages = MutableLiveData<ArrayList<MessageHeader>>().apply {

        value=totalMessages()

    }

    private val _inbox = MutableLiveData<Inbox>().apply {

        value=generateInbox()

    }

    fun getInbox(): Inbox
    {
        return generateInbox()
    }
    fun totalMessages() : ArrayList<MessageHeader>
    {
        val inbox = generateInbox()
        for (lowMessage in inbox.lowPriority )
        {
            inbox.highPriority.add(lowMessage)
        }
        return inbox.highPriority
    }


    fun generateInbox(): Inbox
    {
        val message1= MessageHeader("Zac",
            "https://st-listas.20minutos.es/images/2016-01/406254/4881186_640px.jpg",
            "Hi",
            "19-07-2019")
        val message2= MessageHeader("Pete",
            "https://st-listas.20minutos.es/images/2016-01/406254/4881186_640px.jpg","Bye",
            "19-08-2019")

        val message3= MessageHeader("Ariana Grande",
            "https://los40es00.epimg.net/los40/imagenes/2019/08/11/actualidad/1565512687_081489_1565512863_noticia_normal.jpg",
            "Hi",
            "19-07-2019")
        val message4= MessageHeader("Miley Cyrus",
            "https://www.diariogol.com/uploads/s1/64/82/48/6/miley-cyrus_16_643x397.jpeg","Bye",
            "19-08-2019")

        val messagesHighPriority = arrayListOf(message1,message2)
        val messagesLowPriority = arrayListOf(message3,message4)
        val inbox=Inbox(messagesHighPriority,messagesLowPriority)

        return inbox
    }
    val messagesHeader: MutableLiveData<ArrayList<MessageHeader>> = _messages
    val inbox: MutableLiveData<Inbox> = _inbox


}