package com.example.firebasechatapp.fragment


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.firebasechatapp.R
import com.example.firebasechatapp.SignInActivity
import com.example.firebasechatapp.glide.GlideApp
import com.example.firebasechatapp.util.FirestoreUtil
import com.example.firebasechatapp.util.StorageUtil
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.fragment_my_account.*
import kotlinx.android.synthetic.main.fragment_my_account.view.*
import kotlinx.android.synthetic.main.fragment_my_account.view.editText_name
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.newTask
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast
import java.io.ByteArrayOutputStream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MyAccountFragment : Fragment() {

    private val RC_SELECT_IMAGE = 2
    private lateinit var selectedImageByte : ByteArray
    private var pictureJustChanged = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_my_account, container, false)

        view.apply {
            imageView_profile_picture.setOnClickListener {
                val intent = Intent().apply {
                    type = "image/*"
                    action = Intent.ACTION_GET_CONTENT
                    putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg","image/png"))
                }

                startActivityForResult(Intent.createChooser(intent,"Select Image"),RC_SELECT_IMAGE)
            }

            btn_save.setOnClickListener {
                if(::selectedImageByte.isInitialized)
                    StorageUtil.uploadeProfilePhoto(selectedImageByte){imagePath ->
                        FirestoreUtil.updateCurrentUser(editText_name.text.toString(),
                            editText_bio.text.toString(),imagePath)

                    }
                else
                    FirestoreUtil.updateCurrentUser(editText_name.text.toString(),
                        editText_bio.text.toString(),null)
                    toast("Saving")
            }

            btn_sign_out.setOnClickListener {
                AuthUI.getInstance().signOut(this@MyAccountFragment.context!!)
                    .addOnCompleteListener {
                        startActivity(intentFor<SignInActivity>().newTask().clearTask())
                    }
            }
        }
    return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == RC_SELECT_IMAGE && data != null && data.data !=null){
            val selectImagePath = data.data
            val selectImageBmp = MediaStore.Images.Media.getBitmap(activity?.contentResolver,selectImagePath)
            val outputStream = ByteArrayOutputStream()
            selectImageBmp.compress(Bitmap.CompressFormat.JPEG,90,outputStream)
            selectedImageByte = outputStream.toByteArray()

            GlideApp.with(this)
                .load(selectImageBmp)
                .into(imageView_profile_picture)

            pictureJustChanged = true
        }
    }

    override fun onStart() {
        super.onStart()
        FirestoreUtil.getCurrentUser { user ->
            if(this@MyAccountFragment.isVisible){
                editText_name.setText(user.name)
                editText_bio.setText(user.bio)
                if(!pictureJustChanged && user.profilePicturePath !=null)
                    GlideApp.with(this)
                        .load(StorageUtil.pathToReference(user.profilePicturePath))
                        .placeholder(R.drawable.ic_account_circle_)
                        .into(imageView_profile_picture)
            }
        }
    }
}
