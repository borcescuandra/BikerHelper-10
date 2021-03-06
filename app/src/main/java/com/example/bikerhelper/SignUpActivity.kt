package com.example.bikerhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity: AppCompatActivity(){

    private var usernameSignUp: EditText?=null
    private var passwordSignUp: EditText?=null

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth=FirebaseAuth.getInstance()
        usernameSignUp= findViewById(R.id.username_signUp)
        passwordSignUp= findViewById(R.id.password_signUp)


        signUpButton.setOnClickListener{
            //val currentUser=auth.currentUser
            signUpUser()
        }

        val actionbar=supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun signUpUser()
    {
        if(usernameSignUp?.text.toString().isEmpty())
        {
            usernameSignUp?.error="Please enter email"
            usernameSignUp?.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(usernameSignUp?.text.toString()).matches())
        {
            usernameSignUp?.error="Please enter email"
            usernameSignUp?.requestFocus()
            return
        }
        if(passwordSignUp?.text.toString().isEmpty())
        {
            passwordSignUp?.error="Please enter password"
            passwordSignUp?.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(usernameSignUp?.text.toString(), passwordSignUp?.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Sign Up failed.Try again after some time.",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }

}