package com.example.firebaseauth.Model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class userprofile(

    var uid: String? = "",
    var name: String? = "",
    var email: String? = "",
    var age: Int? = -1,
    var gender: String? = "",
    var pass: String? = ""
)
{
    @Exclude
    fun toMap(): Map<String,Any?>{
        return mapOf(
            "uid" to uid,
            "Name" to name,
            "Email" to email,
            "Age" to age,
            "Gender" to gender,
            "Pass" to pass
        )
    }
}