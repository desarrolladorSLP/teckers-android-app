package org.desarrolladorslp.teckersapp.ui.deliverables

import android.content.Context
import android.os.Build
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.deliverable_item.view.*
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.DeliverableHeader
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle



data class DeliverableAdapter(private var deliverablesHeader: ArrayList<DeliverableHeader>) :
    RecyclerView.Adapter<DeliverableAdapter.DeliverableHeaderHolder>() {
    private val SINGLE =1
    private var deliverablePosition:Int =0

    fun add(deliverableHeader: DeliverableHeader, position: Int = -1) {
        var position = position
        position = if (position == -1) itemCount else position
        deliverablesHeader.add(position, deliverableHeader)
        notifyItemInserted(position)
    }

    fun remove(position: Int) {
        if (position < itemCount) {
            deliverablesHeader.removeAt(position)
            notifyItemRemoved(position)
        }else {
            throw Exception("Invalid Position")
        }
    }

    override fun getItemCount() = deliverablesHeader.size

    @RequiresApi(Build.VERSION_CODES.O)

    override fun onBindViewHolder(holder: DeliverableHeaderHolder, position: Int) {
        val itemDeliverableHeader = deliverablesHeader[position]
        holder.bindDeliverableHeader(itemDeliverableHeader,position)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliverableHeaderHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.deliverable_item, parent, false)

        return DeliverableHeaderHolder(view)
    }

    fun setOnItemClickListener(clickListener: DeliverableHeaderClickListener) {
        sClickListener = clickListener
    }


    fun selected(position: Int) {
        deliverablePosition = position
        notifyDataSetChanged()
    }

    interface DeliverableHeaderClickListener {
        fun onItemClick(position: Int)
    }

    companion object {
        private var sSelectedItems: SparseBooleanArray? = SparseBooleanArray()
        private var sClickListener: DeliverableHeaderClickListener? = null
    }


    class DeliverableHeaderHolder(val view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {


        var deliverables_item_layout: RelativeLayout

        init {
            deliverables_item_layout = itemView.findViewById(R.id.deliverable_item_layout)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (sSelectedItems!!.get(this.adapterPosition, false)) {
                sSelectedItems!!.delete(this.adapterPosition)
                deliverables_item_layout.setSelected(false)
            } else {

                sSelectedItems!!.put(this.adapterPosition, true)
                deliverables_item_layout.setSelected(true)
            }
            sClickListener!!.onItemClick(this.adapterPosition)
        }


        @RequiresApi(Build.VERSION_CODES.O)
        fun bindDeliverableHeader(deliverableHeader: DeliverableHeader,position:Int) {

            var imageStatus= when(deliverableHeader.status)
            {
                "TO_DO" ->  R.drawable.ic_deliverable_to_do
                "IN_PROGRESS" -> R.drawable.ic_deliverable_in_progress
                "READY_FOR_REVIEW" -> R.drawable.ic_deliverable_ready_for_review
                "ACCEPTED" -> R.drawable.ic_deliverable_accepted
                "REJECTED" -> R.drawable.ic_deliverable_rejected
                "BLOCKED" -> R.drawable.ic_deliverable_blocked
                "OVERDUE" -> R.drawable.ic_deliverable_overdue
                else -> R.drawable.ic_deliverable_nonstatus
            }


            view.imageStatus.setImageResource(imageStatus)
            val date = LocalDate.parse(deliverableHeader.dueDate).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
            if(position % 2 !=0 ) {
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

            deliverables_item_layout.setSelected(sSelectedItems!!.get(position, false))

        }

    }


}
