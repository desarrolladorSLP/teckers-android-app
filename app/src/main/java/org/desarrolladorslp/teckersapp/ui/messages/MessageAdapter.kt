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


data class MessageAdapter(private val messagesHeader: ArrayList<MessageHeader>) : RecyclerView.Adapter<MessageAdapter.MessageHeaderHolder>()  {

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
                    .transform(CircleTransform(50,0))
                    .fit()
                    .into(view.imageView)

            }
            catch (e: Exception) {
                print("error en mostrar la imagen")
            }

            view.sender.text = messageheader.sender
            view.subject.text = messageheader.subject
            view.timestamp.text = messageheader.timestamp
        }

        companion object {
            private val senderImage_Key = "SENDERIMAGE"
        }

    }

}
