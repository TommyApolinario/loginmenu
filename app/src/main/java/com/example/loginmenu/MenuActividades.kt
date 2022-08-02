package com.example.loginmenu

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.example.loginmenu.R
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.loginmenu.databinding.ActivityMenuActividadesBinding

class MenuActividades : AppCompatActivity() {
    private var mAppBarConfiguration: AppBarConfiguration? = null
    private var binding: ActivityMenuActividadesBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuActividadesBinding.inflate(
            layoutInflater
        )
        setContentView(binding!!.root)
        setSupportActionBar(binding!!.appBarMenuActividades.toolbar)
        binding!!.appBarMenuActividades.fab.setOnClickListener { view ->
            Snackbar.make(view, "No existen mensajes", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawer = binding!!.drawerLayout
        val navigationView = binding!!.navView
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration =
            AppBarConfiguration.Builder( //                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.listaUsuario
            )
                .setOpenableLayout(drawer)
                .build()
        val navController =
            Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_actividades)
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration!!)
        NavigationUI.setupWithNavController(navigationView, navController)


        // si es normal se quitan varias opciones, caso contrario no pasa nada.
        val modo = intent.extras!!.getString("MODO")
        val username = intent.extras!!.getString("USERNAME")
        println("$modo : $username")
        if (modo == "normal") {
            navigationView.menu.removeItem(R.id.nav_gallery)
            navigationView.menu.removeItem(R.id.listaUsuario)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val modo = intent.extras!!.getString("MODO")
        val username = intent.extras!!.getString("USERNAME")
        val txtUsuario = findViewById<View>(R.id.usuario) as TextView
        val txtModo = findViewById<View>(R.id.lbmodo) as TextView
        txtUsuario.text = username
        txtModo.text = modo

        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_actividades, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_actividades)
        return (NavigationUI.navigateUp(navController, mAppBarConfiguration!!)
                || super.onSupportNavigateUp())
    }
}