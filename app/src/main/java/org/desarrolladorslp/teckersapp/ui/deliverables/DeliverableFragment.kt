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
import org.desarrolladorslp.teckersapp.ui.teckers.TeckerViewModel

class DeliverableFragment : Fragment(),DeliverableAdapter.DeliverableHeaderClickListener {

    private lateinit var deliverablesViewModel: DeliverableViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<DeliverableAdapter.DeliverableHeaderHolder>
    private lateinit var teckersViewModel: TeckerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deliverablesViewModel =
        ViewModelProviders.of(activity!!).get(DeliverableViewModel::class.java)
        teckersViewModel =
            ViewModelProviders.of(activity!!).get(TeckerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_deliverables, container, false)
        teckersViewModel._selectedTecker.observe(activity as AppCompatActivity, Observer{ selectedTecker ->
            if(selectedTecker.teckerId!="")
            {
                deliverablesViewModel.getTeckerDeliverables(selectedTecker.teckerId)
            }else {
                deliverablesViewModel.getDeliverables()
            }
        })
        deliverablesViewModel._deliverables.observe(activity as AppCompatActivity, Observer{ deliverables ->
            viewAdapter = DeliverableAdapter(deliverables)
            (viewAdapter as DeliverableAdapter).setOnItemClickListener(this)
            recyclerView= root.findViewById<RecyclerView>(R.id.deliverablesList).apply{
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = viewAdapter
            }
        })

        return root
    }
    override fun onItemClick(position: Int) {
        (viewAdapter as DeliverableAdapter).selected(position)
    }
}
