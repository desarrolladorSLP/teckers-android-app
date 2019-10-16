package org.desarrolladorslp.teckersapp.ui.deliverables


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.desarrolladorslp.teckersapp.R

class DeliverableFragment : Fragment() {

    private lateinit var deliverablesViewModel: DeliverableViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<DeliverableAdapter.DeliverableHeaderHolder>
    private lateinit var viewManager: RecyclerView.LayoutManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deliverablesViewModel =
            ViewModelProviders.of(this).get(DeliverableViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_deliverables, container, false)
        viewManager = LinearLayoutManager(context)
        deliverablesViewModel._deliverables.observe(activity as AppCompatActivity, Observer{ deliverables ->

            viewAdapter = DeliverableAdapter(deliverables)
            recyclerView= root.findViewById<RecyclerView>(R.id.deliverablesList).apply{
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }

        })

        deliverablesViewModel.createDeliverables()
        return root
    }



}
