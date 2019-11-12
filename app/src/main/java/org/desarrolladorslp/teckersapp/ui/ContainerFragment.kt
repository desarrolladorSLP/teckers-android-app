package org.desarrolladorslp.teckersapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import org.desarrolladorslp.teckersapp.MainActivity.Companion.ROLE_PARENT
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.ui.deliverables.DeliverableFragment
import org.desarrolladorslp.teckersapp.ui.teckers.TeckerFragment
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

        if(!ROLE_PARENT && teckersSize>1)
        {
            val teckers  = TeckerFragment()
            this.childFragmentManager.beginTransaction()
                .add(R.id.fragment_container_layout,teckers)
                .commit()
            teckers.onCreateView(inflater,container,savedInstanceState)
            root= teckers.view

        }
        else{
            val deliverables  = DeliverableFragment()
            this.childFragmentManager.beginTransaction()
                .replace(R.id.content_deliverables_layout,deliverables)
                .commit()
        }
        return root
    }
}