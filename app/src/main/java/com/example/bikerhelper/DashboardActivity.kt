package com.example.bikerhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var  navView: NavigationView
    private lateinit var mToggle: ActionBarDrawerToggle
    private lateinit var locationViewModel:LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        locationViewModel= ViewModelProvider(this).get(LocationViewModel::class.java)
        locationViewModel.deleteAll()

        StartRouteBtn?.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
            finish()
        }

        mDrawerLayout=findViewById(R.id.drawerLayout)
        navView=findViewById(R.id.navigationView)
        mToggle= ActionBarDrawerToggle(this,mDrawerLayout,R.string.logIn,R.string.createAccount)
        mDrawerLayout.addDrawerListener(mToggle)
        mToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navRegister->{
                startActivity(Intent(this,LoginActivity::class.java))
            }
            R.id.navNewAccount->{
                startActivity(Intent(this,SignUpActivity::class.java))
            }
            R.id.navAbout->{
                startActivity(Intent(this,AboutUsActivity::class.java))
            }
            R.id.navTerms->{
                startActivity(Intent(this,TermsConditionsActivity::class.java))
            }
            R.id.tiltangle->{
                startActivity(Intent(this, TiltAngleActivity::class.java))
            }
            R.id.mapsOption->{
                startActivity(Intent(this, MapsActivity::class.java))
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (mToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)

    }
}