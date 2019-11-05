package org.desarrolladorslp.teckersapp.model

import com.google.gson.annotations.SerializedName

class LoggedUser {

    @SerializedName("access_token")
    var accessToken: String? = null
    var expiresIn: Long = 0
    var isValidated: Boolean = false
    var name: String? = null
    var email: String? = null
    var isEnabled: Boolean = false
    var roles: Array<String> = arrayOf()

    fun hasRole(role:String):Boolean
    {
        var isRole=false
        roles.forEach {
            if(it == role)
            {
                isRole=true
            }
        }
        return isRole
    }
}


