package com.apolis.homero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.widget.TextViewCompat
import com.apolis.homero.R
import com.apolis.homero.helpers.SessionManager
import com.apolis.homero.helpers.d
import com.apolis.homero.helpers.openActivity
import com.apolis.homero.ui.property.PropertyActivity
import com.apolis.homero.ui.todo.TodoActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.nav_header.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        d("In main activity")
        setUpToolbar()
        setUpActivity()
    }

    private fun setUpToolbar() {
        toolbar.title = ""
        toolbar_title.text = "Homero"
        TextViewCompat.setTextAppearance(toolbar_title, R.style.GothamMediumAppearance)
        setSupportActionBar(toolbar)
    }

    private fun setUpActivity() {
        var drawerLayout = drawer_layout
        var navView = nav_view
        var toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,0,0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        //Setting up header of the drawer layout
        var sessionManager = SessionManager.getInstance()
        var headerView = navView.getHeaderView(0)
        headerView.text_view_name.text = sessionManager.user.name
        headerView.text_view_email.text = sessionManager.user.email

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_properties -> openActivity(this, PropertyActivity::class.java, null)
            R.id.menu_todo -> openActivity(this, TodoActivity::class.java, null)
            R.id.menu_logout -> SessionManager.getInstance().logout()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
    }
}