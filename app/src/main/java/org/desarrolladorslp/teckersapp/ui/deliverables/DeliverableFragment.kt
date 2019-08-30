package org.desarrolladorslp.teckersapp.ui.deliverables

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.desarrolladorslp.teckersapp.R

class DeliverableFragment : Fragment() {

    private lateinit var deliverableViewModel: DeliverableViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        deliverableViewModel =
            ViewModelProviders.of(this).get(DeliverableViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_deliverables, container, false)
        val textView: TextView = root.findViewById(R.id.text_deliverables)
        deliverableViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}