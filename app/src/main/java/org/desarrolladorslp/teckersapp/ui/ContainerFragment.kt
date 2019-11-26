package org.desarrolladorslp.teckersapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.desarrolladorslp.teckersapp.MainActivity.Companion.ROLE_ADMINISTRATOR
import org.desarrolladorslp.teckersapp.MainActivity.Companion.ROLE_MENTOR
import org.desarrolladorslp.teckersapp.MainActivity.Companion.ROLE_PARENT
import org.desarrolladorslp.teckersapp.MainActivity.Companion.ROLE_TECKER
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.ui.batches.BatchesFragment
import org.desarrolladorslp.teckersapp.ui.deliverables.DeliverableFragment
import org.desarrolladorslp.teckersapp.ui.programs.ProgramBatchFragment
import org.desarrolladorslp.teckersapp.ui.teckers.TeckersFragment
import org.desarrolladorslp.teckersapp.ui.teckers.TeckerViewModel
import org.desarrolladorslp.teckersapp.ui.teckers.TeckersFragment.Companion.teckerId

class ContainerFragment:Fragment() {
    private lateinit var teckersViewModel: TeckerViewModel
    private var teckersSize=0
    override fun onCreate(savedInstanceState: Bundle?) {
        teckersViewModel =
            ViewModelProviders.of(requireActivity()).get(TeckerViewModel::class.java)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         var root = inflater.inflate(R.layout.fragment_container, container, false)

        if(ROLE_ADMINISTRATOR)
        {
            this.childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_layout, BatchesFragment())
                .commit()
        }
        else if(ROLE_PARENT || ROLE_MENTOR)
        {
            teckersViewModel._teckers.observe(this, Observer { teckers->
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
            if(ROLE_PARENT)
            {
                teckersViewModel.getParentTeckers()
            }
            if(ROLE_MENTOR)
            {
                teckersViewModel.getMentorTeckers()
            }

        }
        else if(ROLE_TECKER)
        {
            this.childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_layout, DeliverableFragment())
                .commit()
        }
        return root
    }
}