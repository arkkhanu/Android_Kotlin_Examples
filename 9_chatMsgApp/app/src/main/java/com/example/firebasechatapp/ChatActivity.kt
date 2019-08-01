package com.example.firebasechatapp

import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasechatapp.model.MessageType
import com.example.firebasechatapp.model.TextMessage
import com.example.firebasechatapp.util.FirestoreUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ListenerRegistration
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.activity_chat.*
import org.jetbrains.anko.toast
import java.util.*

class ChatActivity : AppCompatActivity() {

    private lateinit var messageListenerRegistration: ListenerRegistration
    private var shouldInitRecyclerView = true
    private lateinit var messageSection: Section

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra(AppConstants.USER_NAME)
        val otherUserId = intent.getStringExtra(AppConstants.USER_ID)
        FirestoreUtil.getOrCreateChatChannel(otherUserId) { channelId ->
            messageListenerRegistration = FirestoreUtil.addChatMessageListener(channelId, this, this::updateRecyclerView)

            imageView_send.setOnClickListener {
                val messagetoSend = TextMessage(
                    editText_message.text.toString(),
                    Calendar.getInstance().time,
                    FirebaseAuth.getInstance().currentUser!!.uid,
                    MessageType.TEXT
                )
                editText_message.setText("")
                FirestoreUtil.sendMessage(messagetoSend, channelId)
            }

            fab_send_image.setOnClickListener {
//                TODO() Send image message
            }

        }

    }

    private fun updateRecyclerView(messages: List<Item>) {
        fun init(){
            recycler_view_messages.apply {
                layoutManager = LinearLayoutManager(this@ChatActivity)
                adapter = GroupAdapter<ViewHolder>().apply {
                    messageSection = Section(messages)
                    this.add(messageSection)
                }
            }
            shouldInitRecyclerView = false
        }

        fun updateItem() = messageSection.update(messages)

        if (shouldInitRecyclerView)
            init()
        else
            updateItem()

        recycler_view_messages.scrollToPosition(recycler_view_messages.adapter!!.itemCount -1)
    }
}
