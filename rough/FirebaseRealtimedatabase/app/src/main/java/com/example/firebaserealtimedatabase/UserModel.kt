package com.example.firebaserealtimedatabase

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class UserModel() {

    var id: String? = null
    var name: String? = null
    var email: String? = null
    var age: String? = null
    var password: String? = null

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "name" to name,
            "email" to email,
            "age" to age,
            "pass" to password
        )
    }
}
