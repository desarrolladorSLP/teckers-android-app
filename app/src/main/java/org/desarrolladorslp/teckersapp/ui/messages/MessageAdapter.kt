package org.desarrolladorslp.teckersapp.ui.messages

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.message_item.view.*
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.MessageHeader
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import java.net.URL
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList


data class MessageAdapter(private val messagesHeader: ArrayList<MessageHeader>) : RecyclerView.Adapter<MessageAdapter.MessageHeaderHolder>()  {

    fun add(messageHeader: MessageHeader, position: Int) {
        var position = position
        position = if (position == -1) itemCount else position
        messagesHeader.add(position, messageHeader)
        notifyItemInserted(position)
    }

    fun remove(position: Int) {
        if (position < itemCount) {
            messagesHeader.removeAt(position)
            notifyItemRemoved(position)
        }

    }
    override fun getItemCount() = messagesHeader.size

    override fun onBindViewHolder(holder: MessageHeaderHolder, position: Int) {
        val itemMessageHeader = messagesHeader[position]
        holder.bindMessageHeader(itemMessageHeader)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHeaderHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_item, parent, false)

        return MessageHeaderHolder(view)
    }
    class MessageHeaderHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {


        private var messageheader: MessageHeader? = null

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }

        fun bindMessageHeader(messageheader: MessageHeader) {
            this.messageheader = messageheader

            try {
                Picasso.get()
                    .load(messageheader.senderImage)
                    .centerCrop()
                    .transform(CircleTransform(30,0))
                    .fit()
                    .into(view.senderImage)

            }
            catch (e: Exception) {
                print("Error show image")
            }

            view.sender.text = messageheader.sender
            view.subject.text = messageheader.subject

            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
            val myDate = simpleDateFormat.parse(messageheader.timestamp)
            view.timestamp.text = myDate.toString()
        }


    }




}
