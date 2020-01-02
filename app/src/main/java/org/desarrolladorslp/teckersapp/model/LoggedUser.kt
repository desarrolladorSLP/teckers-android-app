package org.desarrolladorslp.teckersapp.model

import com.google.gson.annotations.SerializedName
import org.desarrolladorslp.teckersapp.R

class LoggedUser {

    @SerializedName("access_token")
    var accessToken: String? = null
    var expiresIn: Long = 0
    var isValidated: Boolean = false
    var name: String? = null
    var email: String? = null
    var isEnabled: Boolean = false
    var roles: Array<String> = arrayOf()

    fun hasRole(checkRole:String) = roles.fold(false) { acc, role -> acc || (checkRole == role) }
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
}

