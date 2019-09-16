package org.desarrolladorslp.teckersapp.ui.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.desarrolladorslp.teckersapp.R

class PriorityholderFragment : Fragment()
{
    private lateinit var pageViewModel: PageViewModel
    private lateinit var messageViewModel: MessageViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<MessageAdapter.MessageHeaderHolder>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        messageViewModel =
            ViewModelProviders.of(this).get(MessageViewModel::class.java)

        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setInbox(messageViewModel.getInbox())
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.content_messages, container, false)
        viewManager = LinearLayoutManager(context)
        viewAdapter = MessageAdapter(pageViewModel.getmessages())

        recyclerView= root.findViewById<RecyclerView>(R.id.messagesList).apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        return root
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): PriorityholderFragment {
            return PriorityholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}