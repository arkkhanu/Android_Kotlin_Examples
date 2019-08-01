package com.example.firebaseauth

import android.content.Context

fun Context.Toast(msg:String){
    android.widget.Toast.makeText(applicationContext,msg, android.widget.Toast.LENGTH_SHORT).show()
}