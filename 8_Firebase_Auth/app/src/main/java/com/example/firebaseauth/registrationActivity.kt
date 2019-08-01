package com.example.firebaseauth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseauth.Model.userprofile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registration.*


class registrationActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var mDatabase : DatabaseReference
    private var gender : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val pro = ProgressDialog(this)

        auth = FirebaseAuth.getInstance()

        radio_.setOnCheckedChangeListener { group, checkedid ->
            when (checkedid){
                R.id.male_   -> gender = "Male"
                R.id.female_ -> gender = "Femle"
                else -> return@setOnCheckedChangeListener
            }
        }


        btn_registered.setOnClickListener {
            if(_name_.text.toString().isEmpty()){
                _name_.error = "Can't be Empty"
                return@setOnClickListener
            }
            if (_age_.text.toString().toInt() == null){
                _age_.error = "Can't be Empty"
                return@setOnClickListener
            }

            if(parsing(_age_.text.toString()) <12){
                _age_.error = "Age should be greater 12"
                return@setOnClickListener
            }
            if(gender == null){
                male_.error = "Can't be Empty"
                return@setOnClickListener
            }
            if(_pass_.text.toString().trim().isEmpty()){
                _pass_.error = "Can't be Empty"
                return@setOnClickListener
            }
            if(_confirmpass_.text.toString().trim().isEmpty()){
                _confirmpass_.error = "Can't be Empty"
                return@setOnClickListener
            }
            if(!matchingpass(_pass_.text.toString().trim(),_confirmpass_.text.toString().trim())){
                _confirmpass_.error = "Password doesn't match"
                return@setOnClickListener
            }
            else{
                pro.setTitle("Creating")
                pro.setMessage("Please wait")
                pro.show()
                auth.createUserWithEmailAndPassword(_email_.text.toString().trim(),_pass_.text.toString().trim())
                    .addOnCompleteListener { task ->
                        pro.cancel()
                        if (task.isSuccessful){
                            pro.setTitle("Registering")
                            pro.show()
                           val isOk =  writetodatabase(null,
                                _name_.text.toString(),
                                _email_.text.toString(),
                                _age_.text.toString().toInt(),
                                gender,
                                _pass_.text.toString())
                            if (isOk == true){
                            pro.cancel()
                            startActivity(Intent(this,MainActivity::class.java))
                            finish()}
                            else{
                                pro.cancel()
                                Toast("Failed to registered")
                            }
                        }
                        else{
                            pro.cancel()
                            Toast("Failed Registration")
                        }
                    }
            }


        }

        sign_in_tv_btn.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }


    }

    private fun matchingpass(p1: String, p2: String): Boolean{
        return p1 == p2
    }

    private fun parsing(value: String):Int{
        val num = value.toInt()
        return num
    }


    private fun writetodatabase( uid: String?,
                                 name: String?,
                                 email: String? ,
                                 age: Int? = -1,
                                 gender: String?,
                                 pass: String?): Boolean{
        var isOk = false
        mDatabase = FirebaseDatabase.getInstance().getReference("Users")
        val uid = mDatabase.push().key
        val user = userprofile(uid,name,email,age,gender,pass)
        mDatabase.child(uid!!).setValue(user).addOnCompleteListener {
            if (it.isSuccessful){
                isOk = true
            }
            else{
                isOk = false
            }
        }
        return isOk
    }



}
