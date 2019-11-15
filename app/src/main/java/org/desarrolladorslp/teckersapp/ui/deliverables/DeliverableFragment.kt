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

class DeliverableFragment : Fragment(),DeliverableAdapter.DeliverableHeaderClickListener {

    private lateinit var deliverablesViewModel: DeliverableViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<DeliverableAdapter.DeliverableHeaderHolder>
    private var teckerId=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deliverablesViewModel =
            ViewModelProviders.of(this).get(DeliverableViewModel::class.java)
        /*try{
            teckerId = this.arguments!!.getString("teckerId")!!
        }catch (e:Exception)
        {

        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_deliverables, container, false)
        deliverablesViewModel._deliverables.observe(activity as AppCompatActivity, Observer{ deliverables ->
            viewAdapter = DeliverableAdapter(deliverables)
            (viewAdapter as DeliverableAdapter).setOnItemClickListener(this)
            recyclerView= root.findViewById<RecyclerView>(R.id.deliverablesList).apply{
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = viewAdapter
            }
        })
        if(teckerId!="")
        {
            deliverablesViewModel.getTeckerDeliverables(teckerId)
        }else {
            deliverablesViewModel.getDeliverables()
        }
        return root
    }
    override fun onItemClick(position: Int) {
        (viewAdapter as DeliverableAdapter).selected(position)
    }
}
