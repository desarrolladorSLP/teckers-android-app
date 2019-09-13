package org.desarrolladorslp.teckersapp.ui.messages

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.desarrolladorslp.teckersapp.model.MessageHeader
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.text.method.TextKeyListener.clear
import android.widget.Adapter
import java.util.*
import kotlin.Comparator



class MessageSectionedRecycleViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mContext: Context? = null
    val SECTION_TYPE = 0
    var mValid = true
    var mSectionResourceId: Int = 0
    var mTextResourceId: Int = 0
    var mLayoutInflater: LayoutInflater? = null
    lateinit var mBaseAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
    val mSections = SparseArray<Section>()

    fun MessageSectionedRecycleViewAdapter(
         context: Context,
         sectionResourceId: Int,
         textResourceId: Int,
         baseAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
    ) {
        mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        mSectionResourceId = sectionResourceId

        mTextResourceId = textResourceId
        mBaseAdapter = baseAdapter
        mContext = context
        mBaseAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {

            override fun onChanged() {
                mValid = mBaseAdapter.itemCount > 0
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                mValid = mBaseAdapter.itemCount > 0
                notifyItemRangeChanged(positionStart, itemCount)
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                mValid = mBaseAdapter.itemCount > 0
                notifyItemRangeInserted(positionStart, itemCount)
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                mValid = mBaseAdapter.itemCount > 0
                notifyItemRangeRemoved(positionStart, itemCount)
            }
        })
    }

    class SectionViewHolder(view: View, mTextResourceid: Int) : RecyclerView.ViewHolder(view) {
        var title: TextView
        init {
            title = view.findViewById(mTextResourceid)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, typeView: Int): RecyclerView.ViewHolder {

        if (typeView == SECTION_TYPE) {

            val view = LayoutInflater.from(mContext).inflate(mSectionResourceId, parent, false)

            return SectionViewHolder(view, mTextResourceId)

        } else {

            return mBaseAdapter.onCreateViewHolder(parent, typeView - 1)

        }

    }

    override fun onBindViewHolder(sectionViewHolder: RecyclerView.ViewHolder, position: Int) {
        if (isSectionHeaderPosition(position)) {

            (sectionViewHolder as SectionViewHolder).title.setText(mSections.get(position).title)

        } else {

            mBaseAdapter.onBindViewHolder(sectionViewHolder, sectionedPositionToPosition(position))
        }
    }


    override fun getItemViewType(position: Int): Int {

        return if (isSectionHeaderPosition(position))
            SECTION_TYPE
        else
            mBaseAdapter.getItemViewType(sectionedPositionToPosition(position)) + 1

    }

    class Section(internal var firstPosition: Int, title: CharSequence) {
        internal var sectionedPosition: Int = 0
        var title: CharSequence
            internal set

        init {
            this.title = title
        }
    }

    fun setSections(sections: Array<Section>) {

        mSections.clear()
        Arrays.sort(sections, object : Comparator<Section> {
            override fun compare(o: Section, o1: Section): Int {
                return if (o.firstPosition === o1.firstPosition)
                    0
                else
                    if (o.firstPosition < o1.firstPosition) -1 else 1
            }
        })

        var offset = 0 // offset positions for the headers we're adding

        for (section in sections) {

            section.sectionedPosition = section.firstPosition + offset

            mSections.append(section.sectionedPosition, section)

            ++offset

        }



        notifyDataSetChanged()

    }


    fun positionToSectionedPosition(position: Int): Int {

        var offset = 0

        for (i in 0 until mSections.size()) {

            if (mSections.valueAt(i).firstPosition > position) {

                break

            }

            ++offset

        }

        return position + offset

    }


    fun sectionedPositionToPosition(sectionedPosition: Int): Int {

        if (isSectionHeaderPosition(sectionedPosition)) {
            return RecyclerView.NO_POSITION
        }
        var offset = 0
        for (i in 0 until mSections.size()) {

            if (mSections.valueAt(i).sectionedPosition > sectionedPosition) {
                break
            }
            --offset
        }
        return sectionedPosition + offset

    }


    fun isSectionHeaderPosition(position: Int): Boolean {

        return mSections.get(position) != null

    }


    override fun getItemId(position: Int): Long {

        if (isSectionHeaderPosition(position))
            return Long.MAX_VALUE - mSections.indexOfKey(position)
        else
            return mBaseAdapter.getItemId(sectionedPositionToPosition(position))

    }

    override fun getItemCount(): Int {

        return if (mValid) mBaseAdapter.itemCount + mSections.size() else 0

    }
}

