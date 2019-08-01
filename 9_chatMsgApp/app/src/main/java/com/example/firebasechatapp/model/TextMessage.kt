package com.example.firebasechatapp.model

import java.util.*

data class TextMessage(
    val text: String,
    override val time: Date,
    override val senderID: String,
    override val type: String = MessageType.TEXT)

    : Message {

    constructor(): this("", Date(0),"")
}