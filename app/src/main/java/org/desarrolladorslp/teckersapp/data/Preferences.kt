package org.desarrolladorslp.teckersapp.data

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {
    val PREFERENCE_NAME="org.desarrolladorslp.teckersapp"
    val SHARED_ROLE_ADMINISTRATOR="shared_role_administrator"
    val SHARED_ROLE_PARENT="shared_role_parent"
    val SHARED_ROLE_MENTOR="shared_role_mentor"
    val SHARED_ROLE_TECKER="shared_role_tecker"
    val SHARED_LOG_OUT="shared_log_out"
    val data : SharedPreferences= context.getSharedPreferences(PREFERENCE_NAME,0)

    var ROLE_ADMINISTRATOR: Boolean
    get() = data.getBoolean(SHARED_ROLE_ADMINISTRATOR,false)
    set(value) = data.edit().putBoolean(SHARED_ROLE_ADMINISTRATOR,value).apply()

    var ROLE_PARENT: Boolean
        get() = data.getBoolean(SHARED_ROLE_PARENT,false)
        set(value) = data.edit().putBoolean(SHARED_ROLE_PARENT,value).apply()

    var ROLE_MENTOR: Boolean
        get() = data.getBoolean(SHARED_ROLE_MENTOR,false)
        set(value) = data.edit().putBoolean(SHARED_ROLE_MENTOR,value).apply()

    var ROLE_TECKER: Boolean
        get() = data.getBoolean(SHARED_ROLE_TECKER,false)
        set(value) = data.edit().putBoolean(SHARED_ROLE_TECKER,value).apply()

    var LOG_OUT: Boolean
        get() = data.getBoolean(SHARED_LOG_OUT,false)
        set(value) = data.edit().putBoolean(SHARED_LOG_OUT,value).apply()
}