package org.desarrolladorslp.teckersapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import org.desarrolladorslp.teckersapp.MainActivity.Companion.ROLE_ADMINISTRATOR
import org.desarrolladorslp.teckersapp.MainActivity.Companion.ROLE_PARENT
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.ui.batches.BatchesFragment
import org.desarrolladorslp.teckersapp.ui.deliverables.DeliverableFragment
import org.desarrolladorslp.teckersapp.ui.programs.ProgramBatchFragment
import org.desarrolladorslp.teckersapp.ui.teckers.TeckersFragment
import org.desarrolladorslp.teckersapp.ui.teckers.TeckerViewModel

class ContainerFragment:Fragment() {
    private lateinit var teckersViewModel: TeckerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        teckersViewModel =
            ViewModelProviders.of(this).get(TeckerViewModel::class.java)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         var root = inflater.inflate(R.layout.fragment_container, container, false)
        teckersViewModel =
            ViewModelProviders.of(this).get(TeckerViewModel::class.java)
        var teckersSize=2
        //teckersViewModel.getParentTeckers()
        //teckersSize= teckersViewModel._teckers.value!!.size
        if(ROLE_PARENT)
        {
            if(teckersSize>1) {
                this.childFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragment_container_layout,
                        TeckersFragment()
                    )
                    .commit()
            }
            else{
                this.childFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_layout,DeliverableFragment())
                    .commit()
            }
        }

        else if(ROLE_ADMINISTRATOR)

        {
            this.childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_layout, BatchesFragment())
                .commit()
        }
        return root
    }
}