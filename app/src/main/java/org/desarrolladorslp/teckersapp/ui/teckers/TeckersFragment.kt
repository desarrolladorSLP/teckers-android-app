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
import org.desarrolladorslp.teckersapp.ui.programs.ProgramBatchFragment


class TeckersFragment : Fragment(), TeckerListFragment.TeckerListListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var  root= inflater.inflate(R.layout.fragment_teckers, container, false)
        val teckers = TeckerListFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.content_teckers_layout, teckers)
            .commit()
        return root
    }

    override fun onTeckerSelected(tecker: Tecker) {
        teckerId =tecker.teckerId
        var deliverables =DeliverableFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.content_teckers_layout, deliverables)
            .commit()
    }
    companion object{
        var teckerId = ""
    }
}