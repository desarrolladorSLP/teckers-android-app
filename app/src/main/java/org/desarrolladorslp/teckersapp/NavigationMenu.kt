package org.desarrolladorslp.teckersapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class NavigationMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigationmenu)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        var role = "parent"
        var navigationOption=0
        if(role=="parent")
        {
            navView.menu.removeItem(R.id.navigation_deliverables)
            navigationOption=R.id.navigation_parent_teckers

        }
        else{
            navView.menu.removeItem(R.id.navigation_parent_teckers)
            navigationOption=R.id.navigation_deliverables
        }
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_messages, navigationOption, R.id.navigation_teams, R.id.navigation_sessions
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
