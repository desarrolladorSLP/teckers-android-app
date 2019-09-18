package org.desarrolladorslp.teckersapp.ui.messages

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.content_messages.*
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.Inbox
import org.desarrolladorslp.teckersapp.model.MessageHeader


class MessageFragment : Fragment() {

    private lateinit var messageViewModel: MessageViewModel
    private lateinit var viewAdapter: MessageAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    private lateinit var  tabs: TabLayout
    private lateinit var myContext: FragmentActivity
    private  lateinit var  managerFragment: FragmentManager

    @SuppressLint("MissingSuperCall")
    override fun onAttach(context: Context) {
        myContext = context as FragmentActivity
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messageViewModel =
            ViewModelProviders.of(this).get(MessageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_messages, container, false)
        managerFragment = myContext.supportFragmentManager
        sectionsPagerAdapter = SectionsPagerAdapter(context!!,managerFragment)
        viewAdapter = MessageAdapter(messageViewModel.totalMessages())
        viewPager = root.findViewById<ViewPager>(R.id.view_pager).apply{
            adapter= sectionsPagerAdapter
        }
        tabs= root.findViewById<TabLayout>(R.id.tabs).apply{
            setupWithViewPager(viewPager)
        }
        return root
    }



}