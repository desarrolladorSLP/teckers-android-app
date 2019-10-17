package org.desarrolladorslp.teckersapp.ui.deliverables

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.deliverable_item.view.*
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.ui.CircleTransform


data class DeliverableAdapter(private val deliverablesHeader: ArrayList<DeliverableHeader>) :
    RecyclerView.Adapter<DeliverableAdapter.DeliverableHeaderHolder>() {
    private var deliverableCount =0

    fun add(deliverableHeader: DeliverableHeader, position: Int) {
        var position = position
        position = if (position == -1) itemCount else position
        deliverablesHeader.add(position, deliverableHeader)
        notifyItemInserted(position)
    }

    fun remove(position: Int) {
        if (position < itemCount) {
            deliverablesHeader.removeAt(position)
            notifyItemRemoved(position)
        }

    }

    override fun getItemCount() = deliverablesHeader.size

    @RequiresApi(Build.VERSION_CODES.O)

    override fun onBindViewHolder(holder: DeliverableHeaderHolder, position: Int) {
        val itemDeliverableHeader = deliverablesHeader[position]
        holder.bindDeliverableHeader(itemDeliverableHeader)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliverableHeaderHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.deliverable_item, parent, false)
        deliverableCount ++

        return DeliverableHeaderHolder(view,deliverableCount)
    }

    class DeliverableHeaderHolder(val view: View, val deliverableCount : Int ) : RecyclerView.ViewHolder(view),
        View.OnClickListener {


        private var deliverableHeader: DeliverableHeader? = null

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            Log.d("RecyclerViewMessage", "CLICK!")
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun bindDeliverableHeader(deliverableHeader: DeliverableHeader) {
            this.deliverableHeader = deliverableHeader
            var imageStatus=0
            when(deliverableHeader.status)
            {
                "ToDo" -> imageStatus = 0
                "In Progress" -> imageStatus = 0
                "Ready for review" -> imageStatus =0
                "Accepted" -> imageStatus =R.drawable.ic_deliverable_accepted
                "Rejected" -> imageStatus =0
                "Blocked" -> imageStatus = R.drawable.ic_deliverable_blocked
                "Overdue" -> imageStatus =0
            }


            view.imageStatus.setImageResource(imageStatus)
            if(deliverableCount % 2 !=0 ) {
                view.description_column1.text = deliverableHeader.description
                view.date_column1.text = deliverableHeader.date
                view.description_column2.text = ""
                view.date_column2.text = ""
            }
            else{
                view.description_column1.text = ""
                view.date_column1.text = ""
                view.description_column2.text = deliverableHeader.description
                view.date_column2.text = deliverableHeader.date
            }

        }

    }

}