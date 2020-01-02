package org.desarrolladorslp.teckersapp.ui.programs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_programs_batches.*
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.Batch
import org.desarrolladorslp.teckersapp.model.Program
import org.desarrolladorslp.teckersapp.ui.batches.BatchViewModel
import org.desarrolladorslp.teckersapp.ui.batches.BatchesFragment
import org.desarrolladorslp.teckersapp.ui.teckers.TeckersFragment
import java.lang.IllegalStateException

class ProgramBatchFragment: Fragment() {

    lateinit var programSpinner: Spinner
    lateinit var programsViewModel:ProgramViewModel
    lateinit var batchSpinner: Spinner
    lateinit var programTextView:TextView
    lateinit var batchTextView: TextView
    lateinit var batchesViewModel: BatchViewModel
    lateinit var approvalButton : FloatingActionButton
    var selectedProgram: Program? = null
    var selectedBatch: Batch? = null
    lateinit var listener :BatchSelectedListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        programsViewModel =
            ViewModelProviders.of(parentFragment!!).get(ProgramViewModel::class.java)
        batchesViewModel =
            ViewModelProviders.of(parentFragment!!).get(BatchViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        programSpinner = view.findViewById(R.id.spinner_programs) as Spinner
        batchSpinner = view.findViewById(R.id.spinner_batches) as Spinner
        programTextView = view.findViewById(R.id.text_programs) as TextView
        batchTextView = view.findViewById(R.id.text_batches) as TextView
        approvalButton  = view.findViewById(R.id.btn_approval)as FloatingActionButton


        approvalButton.isVisible=false
        batchSpinner.isVisible=false
        programTextView.isVisible=false
        batchTextView.isVisible=false

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
                        programTextView.isVisible=true

                    }else{
                        batchSpinner.isVisible=false
                        approvalButton.isVisible=false
                        programTextView.isVisible=false
                        batchTextView.isVisible=false
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
                        batchTextView.isVisible=true

                    }else{
                        approvalButton.isVisible=false
                        programTextView.isVisible=true
                        batchTextView.isVisible=false
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //result.text=R.string.not_selected_batch.toString()
                }
            }


        })
        approvalButton.setOnClickListener {
            batchesViewModel.onBatchSelected()
            listener.onBatchSelected(selectedBatch!!)
        }


        programsViewModel.getPrograms()

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val  root= inflater.inflate(R.layout.fragment_programs_batches, container, false)

        return root

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = when {
            parentFragment is  BatchSelectedListener -> parentFragment as  BatchSelectedListener
            context is  BatchSelectedListener -> context
            else -> throw IllegalStateException("Container of TeckerListFragment must implement TeckerListListener")
        }
    }
    interface BatchSelectedListener : AdapterView.OnItemSelectedListener{
      fun onBatchSelected(batch:Batch)
    }

}