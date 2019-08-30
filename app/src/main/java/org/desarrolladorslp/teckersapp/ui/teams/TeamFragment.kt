package org.desarrolladorslp.teckersapp.ui.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.desarrolladorslp.teckersapp.R

class TeamFragment : Fragment() {

    private lateinit var teamViewModel: TeamViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        teamViewModel =
            ViewModelProviders.of(this).get(TeamViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_teams, container, false)
        val textView: TextView = root.findViewById(R.id.text_teams)
        teamViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}