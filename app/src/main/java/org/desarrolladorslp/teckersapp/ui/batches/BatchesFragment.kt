package org.desarrolladorslp.teckersapp.ui.batches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.Batch
import org.desarrolladorslp.teckersapp.ui.programs.ProgramBatchFragment
import org.desarrolladorslp.teckersapp.ui.teckers.TeckersFragment

class BatchesFragment: Fragment(), ProgramBatchFragment.BatchListListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBatchSelected(batch: Batch) {
        val teckers = TeckersFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.content_batches_layout, teckers)
            .commit()
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