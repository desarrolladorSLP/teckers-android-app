package org.desarrolladorslp.teckersapp.ui.parentTeckers

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.parent_tecker_item.view.*
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.Tecker
import org.desarrolladorslp.teckersapp.ui.CircleTransform
import org.desarrolladorslp.teckersapp.ui.deliverables.DeliverableFragment


data class ParentTeckerAdapter(private val teckers: ArrayList<Tecker>, private val fragment: Fragment,private val activity :AppCompatActivity) :
    RecyclerView.Adapter<ParentTeckerAdapter.TeckerHolder>() {


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


        return TeckerHolder(view,fragment,activity)
    }

    class TeckerHolder(val view: View,val fragment: Fragment, val activity: AppCompatActivity) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        private var tecker: Tecker? = null

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val deliverables = DeliverableFragment()

            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.content_teckers_layout,deliverables)
                .commit()
        }



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

        }

    }
}