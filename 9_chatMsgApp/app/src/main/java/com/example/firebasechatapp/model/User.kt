package com.example.firebasechatapp.model

data class User(val name:String,
                val bio: String,
                val profilePicturePath : String?) {
    constructor(): this("","",null)
}