package org.desarrolladorslp.teckersapp.model

import com.google.gson.annotations.SerializedName
import org.desarrolladorslp.teckersapp.R
import org.desarrolladorslp.teckersapp.data.SharedApp

class LoggedUser {

    @SerializedName("access_token")
    var accessToken: String? = null
    var expiresIn: Long = 0
    var isValidated: Boolean = false
    var name: String? = null
    var email: String? = null
    var isEnabled: Boolean = false
    var roles: Array<String> = arrayOf()

    fun hasRole(checkRole:String) :Boolean{
        var isRole= false
        for(role in roles)
        {
            if(role==checkRole)
            {
                isRole=true
            }
        }
        return isRole
    }
    fun assigneRole(){
        if(roles.isNotEmpty())
        {
            if(roles[0]=="ROLE_ADMINISTRATOR")
            {
                SharedApp.data.ROLE_ADMINISTRATOR=true
            }else if(roles[0]=="ROLE_PARENT")
            {
                SharedApp.data.ROLE_PARENT=true
            }else if(roles[0]=="ROLE_MENTOR")
            {
                SharedApp.data.ROLE_MENTOR=true
            }else if(roles[0]=="ROLE_TECKER")
            {
                SharedApp.data.ROLE_TECKER=true
            }

        }
    }
}

