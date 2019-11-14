package org.desarrolladorslp.teckersapp.ui.teckers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.Tecker
import org.desarrolladorslp.teckersapp.ui.deliverables.DeliverableFragment
import org.desarrolladorslp.teckersapp.ui.deliverables.TeckerListFragment

class TeckersFragment : Fragment(), TeckerListFragment.TeckerListListener {
    private lateinit var recyclerView: RecyclerView

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var teckersViewModel: TeckerViewModel
    private lateinit var viewAdapter: RecyclerView.Adapter<TeckersAdapter.TeckerHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        teckersViewModel =
//            ViewModelProviders.of(this).get(TeckerViewModel::class.java)
//        teckersViewModel.selectedTecker.observe(this, Observer {
//        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var  root= inflater.inflate(R.layout.fragment_teckers, container, false)
        childFragmentManager.beginTransaction()
            .replace(R.id.content_teckers_layout, TeckerListFragment())
            .commit()
        return root
    }

    override fun onTeckerSelected(tecker: Tecker) {
        childFragmentManager.beginTransaction()
            .replace(R.id.content_teckers_layout,DeliverableFragment())
            .commit()
    }
}