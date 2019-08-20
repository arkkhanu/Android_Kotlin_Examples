package com.example.firebaserealtimedatabase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_signupactivity.*

class Signupactivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var userage_: EditText
    lateinit var username_: EditText
    lateinit var userpass_: EditText
    lateinit var useremail_: EditText
    lateinit var listingview: RecyclerView
    lateinit var arrayliste: ArrayList<UserModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupactivity)


        ref = FirebaseDatabase.getInstance().getReference("Model")

        userage_ = findViewById<EditText>(R.id.userage)
        username_ = findViewById<EditText>(R.id.username_)
        userpass_ = findViewById<EditText>(R.id.userpass)
        useremail_ = findViewById<EditText>(R.id.useremail)

        arrayliste = arrayListOf()

        listingview = findViewById(R.id.listviewmodel)
     //   listingview.adapter = usermodelList(this, arrayliste)
        listingview.layoutManager = LinearLayoutManager(this)


        btn_signup.setOnClickListener {

            getData()
        }


    }

    override fun onStart() {
        super.onStart()

        arrayliste.clear()
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children )
                {

                    val templist  = postSnapshot.getValue(UserModel::class.java)
                    arrayliste.add(templist!!)
                }
                val adapter = usermodelList(this@Signupactivity,arrayliste)
                listingview.adapter = adapter
                listingview.adapter?.notifyItemInserted(arrayliste.size-1)

            }

        })
    }

    private fun getData() {
        var name = username_.text.toString()
        var email = useremail_.text.toString()
        var pass = userpass_.text.toString()
        var age = userage_.text.toString()

        val isOkField = (!name.isEmpty() && !email.isEmpty() && !age.isEmpty() && !pass.isEmpty())

        if (name.isEmpty()) {
            username_.error = "Please Enter Name"
        }
        if (email.isEmpty()) {
            useremail_.error = "Please Enter Email"
        }
        if (age.isEmpty()) {
            userage_.error = "Please Enter Age"
        }
        if (pass.isEmpty()) {
            userpass_.error = "Please Put Password"
        }

        if (isOkField) {
            val newid = ref.push().key
            val model = UserModel()
            model.age=age
            model.email=email
            model.password=pass
            model.name=name
            model.toMap()

            ref.child(newid!!).setValue(model).addOnCompleteListener {

                if (it.isSuccessful) {
                    Toast.makeText(this, "Done...", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error...${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }

            userage_.setText("")
            useremail_.setText("")
            userpass_.setText("")
            username_.setText("")
        }

    }

}
