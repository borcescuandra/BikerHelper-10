package com.example.bikerhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TermsConditionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_conditions)

        val actionbar=supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}