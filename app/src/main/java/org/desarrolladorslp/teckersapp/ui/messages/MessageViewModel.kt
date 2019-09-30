package org.desarrolladorslp.teckersapp.ui.messages

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import org.desarrolladorslp.teckersapp.model.Inbox
import org.desarrolladorslp.teckersapp.model.MessageHeader
import org.desarrolladorslp.teckersapp.service.APIEndpoint
import org.desarrolladorslp.teckersapp.service.MessageService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessageViewModel() : ViewModel() {

    private var messageService = APIEndpoint.instance()?.create(MessageService::class.java);

    val _inbox= MutableLiveData<Inbox>()
    val pageIndex=2
    fun getmessages(priorityIndex : Int) :ArrayList<MessageHeader> {
        if(priorityIndex==pageIndex)
        {
            return _inbox.value!!.lowPriority
        }
        return  _inbox.value!!.highPriority
    }

    fun getInbox()
    {
        var messageCall = messageService?.getMessages()
        messageCall?.enqueue(object :Callback<Inbox> {
            override fun onResponse(call: Call<Inbox>, response: Response<Inbox>) {
                if (response.code() == 200) {
                    _inbox.value = response.body()
                }

            }

            override fun onFailure(call: Call<Inbox>, t: Throwable) {
                val m = t.message;
            }
        })
    }




}