package org.desarrolladorslp.teckersapp.ui.teckers

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.parent_tecker_item.view.*
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.Tecker
import org.desarrolladorslp.teckersapp.ui.CircleTransform

data class TeckersAdapter(private val teckers: ArrayList<Tecker>,
                          val listener: TeckerListener
) :
    RecyclerView.Adapter<TeckersAdapter.TeckerHolder>() {


    fun add(tecker: Tecker, position: Int = -1) {
        var position = position
        position = if (position == -1) itemCount else position
        teckers.add(position, tecker)
        notifyItemInserted(position)
    }

    fun remove(position: Int) {
        if (position < itemCount) {
            teckers.removeAt(position)
            notifyItemRemoved(position)
        }else
        {
            throw Exception("Invalid Position")
        }


    }

    override fun getItemCount() = teckers.size

    override fun onBindViewHolder(holder: TeckerHolder, position: Int) {
        var itemTecker = teckers[position]
        holder.bindChild(itemTecker)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeckerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.parent_tecker_item, parent, false)


        return TeckerHolder(view)
    }

    inner class TeckerHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private var tecker: Tecker? = null



        fun bindChild(tecker: Tecker) {
            this.tecker = tecker

            val screenDensity = view.image.context.resources.displayMetrics.density.toInt()
            val radius = 40 * screenDensity
            try {
                Picasso.get()
                    .load(tecker.imageUrl)
                    .centerCrop()
                    .transform(CircleTransform(radius, 0))
                    .fit()
                    .into(view.image)

            } catch (e: Exception) {
                Log.d("MessageImageException", e.message!!)
            }
            view.name.text = tecker.name
            view.setOnClickListener {
                listener.onTeckerSelected(tecker)
            }
        }

    }

    interface TeckerListener {
        fun onTeckerSelected(tecker: Tecker)
    }
}