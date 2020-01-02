package org.desarrolladorslp.teckersapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_navigationmenu.*
import org.desarrolladorslp.teckersapp.data.SharedApp

class NavigationMenuActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigationmenu)
        logoMenu.setOnClickListener { logOut() }
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_messages, R.id.navigation_deliverables, R.id.navigation_teams, R.id.navigation_sessions
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun logOut()
    {
        SharedApp.data.LOG_OUT=true
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }


}
