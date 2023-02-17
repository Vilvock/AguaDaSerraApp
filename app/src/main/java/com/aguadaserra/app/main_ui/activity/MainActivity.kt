package com.aguadaserra.app.main_ui.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.config_activity.BaseActivity
import com.aguadaserra.app.global_ui.config_activity.SystemBarColor
import com.aguadaserra.app.global_ui.config_activity.ToolbarTint
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NullPointerException

class MainActivity : AppCompatActivity(), BaseActivity {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    private var darkTintColor: Int = 0

//    var idMatchGlobal = ""
//    var tempIdMatchGlobal = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setTheme(R.style.Theme_AguaDaSerraApp)

        darkTintColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)



        navController = findNavController(R.id.nav_host_fragment)


        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver,
            IntentFilter("Notification")
        )

        if (intent.extras != null) {

//            val idTo = intent.getStringExtra("idTo")!!
//
//            if (tempIdMatchGlobal == idTo) return
//
//            idMatchGlobal = idTo
//            tempIdMatchGlobal = idTo
//
//            intent.replaceExtras(null)

            setupNavigationNotification()

//            intent.action = ""
//            intent.data = null
//            intent.flags = 0

        } else {

            setupNavigation()
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun updateSystemBarColor(tint: SystemBarColor) {

    }

    override fun updatebottomNavigationVisibility(hasBottomNavigation: Boolean) {
        if (hasBottomNavigation) {
            nav_view_bottomnav.visibility = View.VISIBLE

            nav_view_bottomnav.setOnNavigationItemSelectedListener { item ->

                navController.popBackStack()

                when (item.itemId) {
                    R.id.navigation_home -> {
                        navController.navigate(R.id.homeFragment)
                    }
                    R.id.navigation_cart-> {
                        navController.navigate(R.id.cartFragment)
                    }
                    R.id.navigation_orders -> {
                        navController.navigate(R.id.ordersFragment)
                    }
                    R.id.navigation_favorites -> {
                        navController.navigate(R.id.favoritesFragment)
                    }
                    R.id.navigation_profile -> {
                        navController.navigate(R.id.mainMenuFragment)
                    }

                }
                true
            }
        } else {
            nav_view_bottomnav.visibility = View.GONE

        }
    }

    override fun updateToolbarVisibility(hasToolbar: Boolean) {

        if (hasToolbar) {
            toolbar.visibility = View.VISIBLE
        } else {
            toolbar.visibility = View.GONE

        }


    }

    override fun updateToolbarTitle(title: String) {
        toolbarTitle.text = title
    }

    override fun updateToolbar(hasBackButton: Boolean) {

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        supportActionBar?.setDisplayShowHomeEnabled(hasBackButton)
        supportActionBar?.setDisplayHomeAsUpEnabled(hasBackButton)
    }


    //normal entrance
    private fun setupNavigation() {

        val graph = navController.navInflater.inflate(R.navigation.main_graph)
        graph.startDestination = R.id.splashFragment
        navController.graph = graph

    }

    //notification caso click na notificacao
    private fun setupNavigationNotification() {

//        val graph = navController.navInflater.inflate(R.navigation.main_graph)
//        graph.startDestination = R.id.homeFragment
//        navController.graph = graph
//
//        Useful(this@MainActivity).showDefaultDialogView(supportFragmentManager, tag = "match")
    }

    //notification caso esteja ja dentro do app
    private val myReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {


            //chamar so se estiver na main como visao principal
//            if (intent.extras != null) {
//
//                val idTo = intent.getStringExtra("idTo")!!
//
//                if (idTo == "opa") return
//                if (tempIdMatchGlobal == idTo) return
//
//                idMatchGlobal = idTo
//                tempIdMatchGlobal = idTo
//
//                Useful(this@MainActivity).showDefaultDialogView(supportFragmentManager, tag = "match")
//            }
        }
    }
}