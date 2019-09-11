package org.desarrolladorslp.teckersapp.ui.messages

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_messages.*
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.MessageHeader

class MessageFragment : Fragment() {

    private lateinit var messageViewModel: MessageViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MessageAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messageViewModel =
            ViewModelProviders.of(this).get(MessageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_messages, container, false)

        viewManager = LinearLayoutManager(context)
        viewAdapter = MessageAdapter(generateData())

        recyclerView= root.findViewById<RecyclerView>(R.id.messagesList).apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        return root
    }

    fun generateData(): ArrayList<MessageHeader>
    {
        val message1= MessageHeader("Zac",
            "https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwixw_flpcXkAhWQd98KHZXeBDwQjRx6BAgBEAQ&url=%2Furl%3Fsa%3Di%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttps%253A%252F%252Fwww.glamour.es%252Fplaceres%252Fcultura%252Farticulos%252Fzac-efron-entrevista-exclusiva-los-vigilantes-de-la-playa-hugo-man%252F26348%26psig%3DAOvVaw207XGsW_REJvvIVaoVOtEW%26ust%3D1568171805206696&psig=AOvVaw207XGsW_REJvvIVaoVOtEW&ust=1568171805206696",
            "Hi",
            "19-07-2019")
        val message2= MessageHeader("Pete",
            "https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.nacionrex.com%2F__export%2F1565470813331%2Fsites%2Fdebate%2Fimg%2F2019%2F08%2F10%2Fariana_grande_crop1565470706508.jpg_943222218.jpg&imgrefurl=https%3A%2F%2Fwww.nacionrex.com%2Fmusica%2Fblackpink-ariana-grande-colaboracion-rumores-pistas-foto-2019-20190810-0016.html&docid=Ua1W__JaJ4jTgM&tbnid=KQWMW235ocRoYM%3A&vet=10ahUKEwio5ejJpsXkAhVJS6wKHTp1A4IQMwh-KAIwAg..i&w=360&h=360&bih=706&biw=1536&q=ariana%20grande&ved=0ahUKEwio5ejJpsXkAhVJS6wKHTp1A4IQMwh-KAIwAg&iact=mrc&uact=8","Bye",
            "19-08-2019")

        val messages = arrayListOf(message1,message2)
        return messages
    }
}