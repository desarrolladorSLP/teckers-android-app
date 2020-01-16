package org.desarrolladorslp.teckersapp.ui.deliverables

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.data.SharedApp
import org.desarrolladorslp.teckersapp.model.Tecker
import org.desarrolladorslp.teckersapp.ui.batches.BatchViewModel
import org.desarrolladorslp.teckersapp.ui.batches.BatchesFragment.Companion.batchId
import org.desarrolladorslp.teckersapp.ui.teckers.TeckersAdapter
import org.desarrolladorslp.teckersapp.ui.teckers.TeckerViewModel
import java.lang.IllegalStateException

class TeckerListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var teckersViewModel: TeckerViewModel
    private lateinit var viewAdapter: RecyclerView.Adapter<TeckersAdapter.TeckerHolder>
    private var listener: TeckerListListener? = null
    private lateinit var batchViewModel:BatchViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        teckersViewModel =
            ViewModelProviders.of(parentFragment!!).get(TeckerViewModel::class.java)
        batchViewModel =
            ViewModelProviders.of(parentFragment!!).get(BatchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_tecker_list, container, false)
        viewManager = GridLayoutManager(context, 2)

        batchViewModel._batchId.observe(parentFragment!!, Observer {selectedBatchId ->
            batchId =selectedBatchId
        })

        teckersViewModel._teckers.observe(parentFragment!!, Observer { teckers ->

            viewAdapter = TeckersAdapter(
                teckers,
                object : TeckersAdapter.TeckerListener {
                    override fun onTeckerSelected(tecker: Tecker) {
                        listener?.onTeckerSelected(tecker)
                    }
                })
            recyclerView = root.findViewById<RecyclerView>(R.id.teckerList).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }

        })

        if(batchId!="")
        {
            teckersViewModel.getBatchTeckers(batchId)
        }else{
            if(SharedApp.data.ROLE_PARENT)
            {
                teckersViewModel.getParentTeckers()
            }
            if(SharedApp.data.ROLE_MENTOR)
            {
                teckersViewModel.getMentorTeckers()
            }

        }
        return root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = when {
            parentFragment is TeckerListListener -> parentFragment as TeckerListListener
            context is TeckerListListener -> context
            else -> throw IllegalStateException("Container of TeckerListFragment must implement TeckerListListener")
        }
    }

    interface TeckerListListener {
        fun onTeckerSelected(tecker: Tecker)
    }
}
