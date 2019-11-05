package org.desarrolladorslp.teckersapp.ui.teckers

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
import kotlinx.android.synthetic.main.fragment_teckers.view.*
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.Tecker
import org.desarrolladorslp.teckersapp.ui.deliverables.DeliverableFragment

class TeckerFragment : Fragment(),
    TeckerAdapter.OnHeadlineSelectedListener{
    private lateinit var teckersViewModel: TeckerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        teckersViewModel =
            ViewModelProviders.of(this).get(TeckerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lateinit var viewManager: RecyclerView.LayoutManager
        lateinit var viewAdapter: RecyclerView.Adapter<TeckerAdapter.TeckerHolder>
        val root= inflater.inflate(R.layout.fragment_teckers, container, false)
        viewManager = GridLayoutManager(context,2)
        teckersViewModel._teckers.observe(activity as AppCompatActivity, Observer{ teckers ->

            viewAdapter = TeckerAdapter(teckers)
            (viewAdapter as TeckerAdapter).setOnHeadlineSelectedListener(this)
            root.teckersList.apply{
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }


        })
        teckersViewModel.getParentTeckers()

        return root
    }

    override fun onTeckerSelected(tecker: Tecker) {

        val deliverables = DeliverableFragment()

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_layout,deliverables)
            .commit()
    }




}