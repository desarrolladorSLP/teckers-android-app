package org.desarrolladorslp.teckersapp.ui.programs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.model.Batch
import org.desarrolladorslp.teckersapp.model.Program
import org.desarrolladorslp.teckersapp.ui.batches.BatchViewModel

class ProgramBatchFragment: Fragment() {

    lateinit var programSpinner: Spinner
    lateinit var programsViewModel:ProgramViewModel
    lateinit var batchSpinner: Spinner
    lateinit var batchesViewModel: BatchViewModel
    var selectedProgram: Program? = null
    var selectedBatch: Batch?=null

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

        val  root= inflater.inflate(R.layout.fragment_programs_batches, container, false)
        programSpinner = root.findViewById(R.id.spinner_programs) as Spinner
        batchSpinner = root.findViewById(R.id.spinner_batches) as Spinner
        programsViewModel._programs.observe(activity as AppCompatActivity, Observer{ programs ->
            var programsArray = arrayOfNulls<String>(programs.size)
            for((i, program) in programs.withIndex())
            {
                programsArray[i]=program.name
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
                    selectedProgram= programs[position]
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
                batchesArray[i]=batch.name
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
                    selectedBatch =batches[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //result.text=R.string.not_selected_batch.toString()
                }
            }


        })
        programsViewModel.getPrograms()
        batchesViewModel.getBatches()




        return root

    }
}