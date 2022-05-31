package com.example.bikerhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.overview_information.*

class OverviewInfoActivity:AppCompatActivity() {

    private lateinit var locationViewModel:LocationViewModel

    companion object{
        private var instance: OverviewInfoActivity? =null
        fun getOverviewInstance(): OverviewInfoActivity{
            return instance!!
        }
    }
    override fun onCreate(savedInstanceState:Bundle?){
        //Create Activity
        super.onCreate(savedInstanceState)
        setContentView(R.layout.overview_information)
        //Instance of ViewModel
        locationViewModel= ViewModelProvider(this).get(LocationViewModel::class.java)
        instance=this

        /**Call method for Summarization information */
        locationViewModel.endRouteInfo()

        button_overview.setOnClickListener {
            /**Delete all from Room*/
            locationViewModel.deleteAll()
            /**Start Dashboard*/
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
    }

}