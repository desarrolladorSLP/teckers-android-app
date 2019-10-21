package org.desarrolladorslp.teckersapp.ui.deliverables

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.deliverable_item.view.*
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.DeliverableHeader
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


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
                "TO_DO" -> imageStatus = R.drawable.ic_deliverable_to_do
                "IN_PROGRESS" -> imageStatus = R.drawable.ic_deliverable_in_progress
                "READY_FOR_REVIEW" -> imageStatus =R.drawable.ic_deliverable_ready_for_review
                "ACCEPTED" -> imageStatus =R.drawable.ic_deliverable_accepted
                "REJECTED" -> imageStatus =R.drawable.ic_deliverable_rejected
                "BLOCKED" -> imageStatus = R.drawable.ic_deliverable_blocked
                "OVERDUE" -> imageStatus =R.drawable.ic_deliverable_overdue
            }


            view.imageStatus.setImageResource(imageStatus)
            val date = LocalDate.parse(deliverableHeader.dueDate).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
            if(deliverableCount % 2 !=0 ) {
                view.description_column1.text = deliverableHeader.title


                view.date_column1.text = date
                view.description_column2.text = ""
                view.date_column2.text = ""
            }
            else{
                view.description_column1.text = ""
                view.date_column1.text = ""
                view.description_column2.text = deliverableHeader.title
                view.date_column2.text = date
            }

        }


    }

}