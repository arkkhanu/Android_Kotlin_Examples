package com.example.firemessage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.FirebaseApp


class Main2Activity : AppCompatActivity() {

//    lateinit var dabasemodel: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.signup_now)


//
//        alreadyone_tv.setOnClickListener {
//            startActivity(Intent(this, MainActivity::class.java))
//        }
//
//
//           fun signupnow() {
//            val age = userage.text.toString().toInt()
//            val name = username_.text.toString()
//            val pass = userpass.text.toString()
//            val email = useremail.text.toString()
//
//            val id: String? = ref.push().key
//            val model: UserModel = UserModel(id!!, name, email, age, pass)
//
//            ref.child(id).setValue(model).addOnCompleteListener {
//                if (it.isSuccessful)
//                {
//                    Toast.makeText(this,"Done.",Toast.LENGTH_SHORT).show()
//                }
//                else{
//                    Toast.makeText(this,"Error ${it.exception}.",Toast.LENGTH_SHORT).show()
//                }
//            }
//
//        }
//
//        btn_signup.setOnClickListener {
//            signupnow()
//        }
    }



}
