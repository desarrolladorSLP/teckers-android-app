package org.desarrolladorslp.teckersapp.ui.programs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.Batch
import org.desarrolladorslp.teckersapp.model.Program
import org.desarrolladorslp.teckersapp.ui.batches.BatchViewModel
import org.desarrolladorslp.teckersapp.ui.teckers.TeckersFragment
import java.lang.IllegalStateException

class ProgramBatchFragment: Fragment() {

    lateinit var programSpinner: Spinner
    lateinit var programsViewModel:ProgramViewModel
    lateinit var batchSpinner: Spinner
    lateinit var batchesViewModel: BatchViewModel
    lateinit var approvalButton : FloatingActionButton
    var selectedProgram: Program? = null
    var selectedBatch: Batch? = null
    var listener: BatchListListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        programsViewModel =
            ViewModelProviders.of(this).get(ProgramViewModel::class.java)
        batchesViewModel =
            ViewModelProviders.of(this).get(BatchViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        batchesViewModel = activity?.run {
            ViewModelProviders.of(this).get(BatchViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        val  root= inflater.inflate(R.layout.fragment_programs_batches, container, false)
        programSpinner = root.findViewById(R.id.spinner_programs) as Spinner
        batchSpinner = root.findViewById(R.id.spinner_batches) as Spinner
        approvalButton  = root.findViewById(R.id.btn_approval)as FloatingActionButton
        approvalButton.isVisible=false
        batchSpinner.isVisible=false
        programsViewModel._programs.observe(activity as AppCompatActivity, Observer{ programs ->
            var programsArray = arrayOfNulls<String>(programs.size)
            for((i, program) in programs.withIndex())
            {
                if(i==0)
                {
                    programsArray[i]=getString(R.string.not_selected_program)
                }
                else{
                    programsArray[i]=program.name
                }

            }
            programSpinner.adapter = ArrayAdapter<String>(context!!,
                android.R.layout.simple_expandable_list_item_1,programsArray)
            programSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                )
                {
                    if(position!=0)
                    {
                        selectedProgram= programs[position]
                        batchSpinner.isVisible=true
                        batchesViewModel.getProgramBatches(selectedProgram!!.id)
                    }else{
                        batchSpinner.isVisible=false
                        approvalButton.isVisible=false
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //result.text=R.string.not_selected_program.toString()
                }
            }
        })

        batchesViewModel._batches.observe(activity as AppCompatActivity, Observer{ batches ->
            var batchesArray = arrayOfNulls<String>(batches.size)
            for((i, batch) in batches.withIndex())
            {
                if(i==0)
                {
                    batchesArray[i]= getString(R.string.not_selected_batch)
                }else {
                    batchesArray[i] = batch.name
                }
            }
            batchSpinner.adapter = ArrayAdapter<String>(context!!,
                android.R.layout.simple_expandable_list_item_1,batchesArray)
            batchSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                )
                {
                    if(position!=0)
                    {

                        selectedBatch=batches[position]
                        batchesViewModel.setBatchId(selectedBatch!!.id)
                        approvalButton.isVisible=true

                    }else{
                        approvalButton.isVisible=false
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //result.text=R.string.not_selected_batch.toString()
                }
            }


        })
        approvalButton.setOnClickListener {
            Toast.makeText(context,"Approval click",Toast.LENGTH_LONG)
                .show()
            listener?.onBatchSelected(selectedBatch!!)
                    }
        programsViewModel.getPrograms()
        return root

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = when {
            parentFragment is BatchListListener -> parentFragment as BatchListListener
            context is BatchListListener -> context
            else -> throw IllegalStateException("Container of ProgramBatchesFragment must implement BatchListListener")
        }
    }
    interface BatchListListener : AdapterView.OnItemSelectedListener
    {
        fun onBatchSelected(batch:Batch)
    }

}