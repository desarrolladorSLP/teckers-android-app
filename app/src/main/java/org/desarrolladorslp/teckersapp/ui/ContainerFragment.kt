package org.desarrolladorslp.teckersapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.data.SharedApp
import org.desarrolladorslp.teckersapp.ui.batches.BatchViewModel
import org.desarrolladorslp.teckersapp.ui.batches.BatchesFragment
import org.desarrolladorslp.teckersapp.ui.deliverables.DeliverableFragment
import org.desarrolladorslp.teckersapp.ui.programs.ProgramBatchFragment
import org.desarrolladorslp.teckersapp.ui.teckers.TeckersFragment
import org.desarrolladorslp.teckersapp.ui.teckers.TeckerViewModel
import org.desarrolladorslp.teckersapp.ui.teckers.TeckersFragment.Companion.teckerId

class ContainerFragment:Fragment() {
    private lateinit var teckersViewModel: TeckerViewModel
    private lateinit var batchesViewModel:BatchViewModel
    private var teckersSize=0
    override fun onCreate(savedInstanceState: Bundle?) {
        teckersViewModel =
        ViewModelProviders.of(activity!!).get(TeckerViewModel::class.java)
        batchesViewModel =
            ViewModelProviders.of(activity!!).get(BatchViewModel::class.java)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         var root = inflater.inflate(R.layout.fragment_container, container, false)

        root=showContent(root)
        return root
    }

    fun showContent(root:View): View
    {
        if(SharedApp.data.ROLE_ADMINISTRATOR)
        {
            this.childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_layout, BatchesFragment())
                .commit()
        }
        else if(SharedApp.data.ROLE_PARENT || SharedApp.data.ROLE_MENTOR)
        {
            teckersViewModel._teckers.observe(activity as AppCompatActivity, Observer { teckers->
                teckersSize = teckers.size

                if(teckersSize>1) {
                    this.childFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container_layout,
                            TeckersFragment()
                        )
                        .commit()
                }
                else{
                    if(teckersSize!=0) {
                        teckerId = teckers[0].teckerId
                        this.childFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container_layout, DeliverableFragment())
                            .commit()
                    }
                }
            })
            if(SharedApp.data.ROLE_PARENT)
            {
                teckersViewModel.getParentTeckers()
            }
            if(SharedApp.data.ROLE_MENTOR)
            {
                teckersViewModel.getMentorTeckers()
            }

        }
        else if(SharedApp.data.ROLE_TECKER)
        {
            this.childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_layout, DeliverableFragment())
                .commit()
        }
        return root

    }
}