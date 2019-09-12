package org.desarrolladorslp.teckersapp.ui.messages

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_messages.*
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.MessageHeader

class MessageFragment : Fragment() {

    private lateinit var messageViewModel: MessageViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MessageAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messageViewModel =
            ViewModelProviders.of(this).get(MessageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_messages, container, false)

        viewManager = LinearLayoutManager(context)
        viewAdapter = MessageAdapter(generateData())

        recyclerView= root.findViewById<RecyclerView>(R.id.messagesList).apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        return root
    }

    fun generateData(): ArrayList<MessageHeader>
    {
        val message1= MessageHeader("Zac",
            "https://st-listas.20minutos.es/images/2016-01/406254/4881186_640px.jpg",
            "Hi",
            "19-07-2019")
        val message2= MessageHeader("Pete",
            "https://st-listas.20minutos.es/images/2016-01/406254/4881186_640px.jpg","Bye",
            "19-08-2019")

        val messages = arrayListOf(message1,message2)
        return messages
    }
}