package com.example.firebaseauth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebaseauth.Model.userprofile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prodi = ProgressDialog(this)

        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().getReference("heros")

        sign_up_tv_btn.setOnClickListener {
            startActivity(Intent(this,registrationActivity::class.java))
            finish()
        }

        logn_btn.setOnClickListener {
            if(_email_l.text.toString().trim().isEmpty()){
                _email_l.error = "Can't be Empty"
                return@setOnClickListener
            }
            if(_pass_l.text.toString().trim().isEmpty()){
                _pass_l.error = "Can't be Empty"
                return@setOnClickListener
            }
            else{
                prodi.setTitle("Signing In")
                prodi.setMessage("Please Wait")
                prodi.show()
                auth.signInWithEmailAndPassword(_email_l.text.toString().trim(),_pass_l.text.toString().trim())
                    .addOnCompleteListener { task ->
                        prodi.cancel()
                    if (task.isSuccessful){
                       val d =  datafromfirbase()
                        Toast(d)
                        startActivity(Intent(this,HomeActivity::class.java))
                        finish()
                    }
                    else{
                        Toast("Wrong Email Password")
                    }

                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
         val currentuser = auth.currentUser
        if(auth.currentUser != null) {
//            datafromfirbase()
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
        else{
            Toast("You have sign out already")
        }
    }


   private fun datafromfirbase(): String{
       var user : userprofile? = null
       var ss : String?  = null
       ref.addValueEventListener(object : ValueEventListener{
           override fun onCancelled(p0: DatabaseError) {
                Toast("Error")
           }

           override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    for(h in p0.children){
                         user = h.getValue(userprofile::class.java)!!
                        break
                    }

                    ss= user!!.email
                    Log.e("user " , "${user!!.name} : ${user!!.uid}" )
                }
           }
       })
       return ss!!
   }




}
