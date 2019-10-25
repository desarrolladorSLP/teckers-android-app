package org.desarrolladorslp.teckersapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import org.desarrolladorslp.teckersapp.MainActivity.Companion.ROLE_PARENT
import org.desarrolladorslp.teckersapp.ui.teckers.TeckerViewModel

class NavigationMenu : AppCompatActivity() {
    private lateinit var teckersViewModel: TeckerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigationmenu)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        //teckersViewModel =
        //    ViewModelProviders.of(this).get(TeckerViewModel::class.java)
        var teckersSize=2
        //teckersViewModel.getParentTeckers()
        //teckersSize= teckersViewModel._teckers.value!!.size
        var navigationOption=0
        if(!ROLE_PARENT && teckersSize>1)
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
