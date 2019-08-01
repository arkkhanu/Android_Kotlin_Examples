package com.example.firebasechatapp.model


data class ChatChannel(val userIds: MutableList<String>) {
    constructor(): this(mutableListOf())
}