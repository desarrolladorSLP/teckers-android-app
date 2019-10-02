package org.desarrolladorslp.teckersapp.ui.messages

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_messages.*
import org.desarrolladorslp.teckersapp.MainActivity

import org.desarrolladorslp.teckersapp.R


class PriorityholderFragment : Fragment()
{
    private lateinit var messageViewModel: MessageViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<MessageAdapter.MessageHeaderHolder>
    private lateinit var viewManager: RecyclerView.LayoutManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        messageViewModel =
            ViewModelProviders.of(this).get(MessageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.content_messages, container, false)
        viewManager = LinearLayoutManager(context)
        messageViewModel._inbox.observe(activity as AppCompatActivity, Observer{ inbox ->
            viewAdapter = MessageAdapter(
                if (arguments?.getInt(ARG_SECTION_NUMBER) != MessageViewModel. LOW_PRIORITY_PAGE_INDEX) inbox.highPriority
                else inbox.lowPriority
            )
            recyclerView= root.findViewById<RecyclerView>(R.id.messagesList).apply{
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }

        })

        messageViewModel._authorizationException.observe(activity as AppCompatActivity, Observer { authorizationException ->
            if (authorizationException != null) {

                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra(AUTH_ERROR, true)
                startActivity(intent)
            }
        })

        messageViewModel._responseException.observe(activity as AppCompatActivity, Observer{ responseException ->
            if(responseException != null)
            {
                val snackbar = Snackbar.make(root,R.string.error_response_messages , Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.retry, View.OnClickListener {

                    Log.d("Messages Reload", "CLICK!")
                })
                snackbar.setActionTextColor(Color.parseColor("#ffffff"))
                val textView = snackbar.view.findViewById(R.id.snackbar_text) as TextView
                textView.textSize = 20f
                snackbar.show()
            }

        })

        messageViewModel.getInbox()
        return root
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"
        val AUTH_ERROR = "AUTH_ERROR"
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
