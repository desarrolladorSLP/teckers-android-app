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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_messages.*
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.Inbox
import org.desarrolladorslp.teckersapp.model.MessageHeader




class MessageFragment : Fragment() {

    private lateinit var messageViewModel: MessageViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MessageAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var  dividerManager: RecyclerView.ItemDecoration

    private lateinit var inbox : Inbox


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messageViewModel =
            ViewModelProviders.of(this).get(MessageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_messages, container, false)
        inbox=messageViewModel.getInbox()
        viewManager = LinearLayoutManager(context)

        viewAdapter = MessageAdapter(messageViewModel.totalMessages())

        recyclerView= root.findViewById<RecyclerView>(R.id.messagesList).apply{
            setHasFixedSize(true)
            layoutManager = viewManager

            adapter = viewAdapter
        }
        recyclerView.addItemDecoration(DividerItemDecoration(context,recyclerView.layoutManager!!.layoutDirection))


        val  sections=arrayOf<MessageSectionedRecycleViewAdapter.Section> (
            MessageSectionedRecycleViewAdapter.Section(0, "High Priority"),
            MessageSectionedRecycleViewAdapter.Section(inbox.highPriority.lastIndex-1, "Low Priority")
        )
        val mSectionedAdapter = MessageSectionedRecycleViewAdapter()
        mSectionedAdapter.MessageSectionedRecycleViewAdapter(context!!,R.layout.section,R.id.section_text,viewAdapter)
        mSectionedAdapter.setSections(sections)
        recyclerView.adapter=mSectionedAdapter
        return root
    }






}