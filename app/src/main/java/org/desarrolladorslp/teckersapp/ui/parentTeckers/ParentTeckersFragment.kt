package org.desarrolladorslp.teckersapp.ui.parentTeckers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.ui.teckers.TeckerViewModel

class ParentTeckersFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var teckersViewModel: TeckerViewModel
    private lateinit var viewAdapter: RecyclerView.Adapter<ParentTeckerAdapter.TeckerHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        teckersViewModel =
            ViewModelProviders.of(this).get(TeckerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var  root= inflater.inflate(R.layout.fragment_parent_teckers, container, false)
        viewManager = GridLayoutManager(context,2)

        teckersViewModel._teckers.observe(activity as AppCompatActivity, Observer{ teckers ->

            viewAdapter = ParentTeckerAdapter(teckers,this,activity as AppCompatActivity)
            recyclerView= root.findViewById<RecyclerView>(R.id.parentTeckersList).apply{
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }


        })
        teckersViewModel.getParentTeckers()

        return root
    }
}