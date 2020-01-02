package org.desarrolladorslp.teckersapp.data

import android.app.Application

class SharedApp:Application() {
    companion object{
        lateinit var data:Preferences
    }

    override fun onCreate() {
        super.onCreate()
        data= Preferences(applicationContext)
    }
}