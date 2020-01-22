package org.desarrolladorslp.teckersapp.ui.batches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.Batch
import org.desarrolladorslp.teckersapp.ui.deliverables.DeliverableFragment
import org.desarrolladorslp.teckersapp.ui.programs.ProgramBatchFragment
import org.desarrolladorslp.teckersapp.ui.teckers.TeckerViewModel
import org.desarrolladorslp.teckersapp.ui.teckers.TeckersFragment

class BatchesFragment: Fragment(), ProgramBatchFragment.BatchSelectedListener {
    override fun onBatchSelected(batch: Batch) {
        ViewModelProviders.of(activity!!).get(BatchViewModel::class.java).setBatchSelected(batch)
        ViewModelProviders.of(activity!!).get(TeckerViewModel::class.java)._teckers.observe(activity as AppCompatActivity, Observer { teckers->
            if(teckers.size>1) {
                val teckers = TeckersFragment()
                childFragmentManager.beginTransaction()
                    .replace(R.id.content_batches_layout, teckers)
                    .commit()
            }
            else{
                if(teckers.size!=0) {
                    ViewModelProviders.of(activity!!).get(TeckerViewModel::class.java).setSelectedTecker(teckers[0])
                    this.childFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_layout, DeliverableFragment())
                        .commit()
                }
            }
        })

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var  root= inflater.inflate(R.layout.fragment_batches, container, false)
        childFragmentManager.beginTransaction()
            .replace(R.id.content_batches_layout, ProgramBatchFragment())
            .commit()

        return root
    }

}