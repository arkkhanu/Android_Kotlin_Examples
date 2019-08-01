package com.example.firebasechatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.startActivity
import java.lang.Exception

class SplachScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach_screen)
       val backgroud = object : Thread(){
           override fun run() {
               try{
                   Thread.sleep(1000)
                   if(FirebaseAuth.getInstance().currentUser == null)
                       return startActivity<SignInActivity>()

                   else
                       return startActivity<MainActivity>()
                   finish()
               }catch (e: Exception){e.printStackTrace()}
           }
       }.start()
    }
}
