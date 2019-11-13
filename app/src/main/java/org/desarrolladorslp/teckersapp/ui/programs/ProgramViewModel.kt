package org.desarrolladorslp.teckersapp.ui.programs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.desarrolladorslp.teckersapp.model.Program

class ProgramViewModel : ViewModel() {

    var _programs = MutableLiveData<ArrayList<Program>>().apply {
        value = getPrograms()
    }

    fun getPrograms() :ArrayList<Program>{
        val programs :ArrayList<Program> = arrayListOf()
        val program1 = Program("1","TECKERS","JAVA WORKSHOP","ELOY")
        programs.add(program1)
        val program2 = Program("2","TECKERS2","JAVA WORKSHOP","ELOY")
        programs.add(program2)
        val program3 = Program("3","TECKERS3","JAVA WORKSHOP","ELOY")
        programs.add(program3)
        val program4 = Program("4","TECKERS4","JAVA WORKSHOP","ELOY")
        programs.add(program4)
        val program5 = Program("5","TECKERS5","JAVA WORKSHOP","ELOY")
        programs.add(program5)
        val program6 = Program("6","TECKERS6","JAVA WORKSHOP","ELOY")
        programs.add(program6)
        val program7 = Program("7","TECKERS7","JAVA WORKSHOP","ELOY")
        programs.add(program7)
        val program8 = Program("8","TECKERS8","JAVA WORKSHOP","ELOY")
        programs.add(program8)

        return programs

    }
}