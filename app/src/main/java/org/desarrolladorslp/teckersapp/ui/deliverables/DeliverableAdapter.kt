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
            var imageStatus=""
            when(deliverableHeader.status)
            {
                "Accepted" -> imageStatus="https://cdn2.glamour.es/uploads/images/thumbs/es/glam/4/s/2017/15/zac_efron_7917_620x698.jpg"
            }


            try {
                Picasso.get()
                    .load(imageStatus)
                    .centerCrop()
                    .transform(CircleTransform(20*view.imageStatus.context.resources.displayMetrics.density.toInt(), 0))
                    .fit()
                    .into(view.imageStatus)

            } catch (e: Exception) {
                Log.d("DeliverImageException", e.message!!)
            }
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