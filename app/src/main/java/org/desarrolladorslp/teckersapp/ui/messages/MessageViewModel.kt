package org.desarrolladorslp.teckersapp.ui.messages

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.Auth
import org.desarrolladorslp.teckersapp.MainActivity
import org.desarrolladorslp.teckersapp.NavigationMenu
import org.desarrolladorslp.teckersapp.exception.AuthorizationException
import org.desarrolladorslp.teckersapp.exception.ResponseException

import org.desarrolladorslp.teckersapp.model.Inbox
import org.desarrolladorslp.teckersapp.model.MessageHeader
import org.desarrolladorslp.teckersapp.service.APIEndpoint
import org.desarrolladorslp.teckersapp.service.MessageService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessageViewModel() : ViewModel() {

    private var messageService = APIEndpoint.instance().create(MessageService::class.java);

    val _inbox = MutableLiveData<Inbox>()
    val _responseException = MutableLiveData<ResponseException?>()
    val _authorizationException = MutableLiveData<AuthorizationException?>()

    fun getmessages(priorityIndex: Int): ArrayList<MessageHeader> {
        if (priorityIndex == LOW_PRIORITY_PAGE_INDEX) {
            return _inbox.value!!.lowPriority
        }
        return _inbox.value!!.highPriority
    }

    fun getInbox() {
        var messageCall = messageService?.getMessages()
        messageCall?.enqueue(object : Callback<Inbox> {
            override fun onResponse(call: Call<Inbox>, response: Response<Inbox>) {
                _inbox.value = response.body()
            }

            override fun onFailure(call: Call<Inbox>, t: Throwable) {
                val m = t.message;

                if (t is ResponseException) {
                    _responseException.value = t
                }else if(t is AuthorizationException)
                {
                    _authorizationException.value = t
                }
            }
        })
    }

    companion object {

        const val LOW_PRIORITY_PAGE_INDEX = 2


    }


}