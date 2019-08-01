package com.example.firebasechatapp.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.NullPointerException
import java.util.*

object StorageUtil {

    private val storageInstance : FirebaseStorage by lazy { FirebaseStorage.getInstance() }
    private val currentuserRef: StorageReference
    get() = storageInstance.reference.child(FirebaseAuth.getInstance().currentUser?.uid ?: throw NullPointerException("UID is Null."))

    fun uploadeProfilePhoto(imageBytes: ByteArray , onSuccess:(imagePath: String)->Unit){
        val ref = currentuserRef.child("profilePicture/${UUID.nameUUIDFromBytes(imageBytes)}")
        ref.putBytes(imageBytes)
            .addOnSuccessListener {
                onSuccess(ref.path)
            }
    }

    fun pathToReference(path:String) = storageInstance.getReference(path)
}