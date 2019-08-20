package com.example.firemessage

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        createnow_tv_singin.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }




        val useremail = findViewById<TextInputEditText>(R.id.useremail_signin)
        val userpass = findViewById<TextInputEditText>(R.id.userpass_signin)
//        val email = useremail.text.toString()
//        val pass = userpass.text.toString()

        fun lodingfuni() {


        }

        btn_signin.setOnClickListener {
            lodingfuni()
        }
    }
}
