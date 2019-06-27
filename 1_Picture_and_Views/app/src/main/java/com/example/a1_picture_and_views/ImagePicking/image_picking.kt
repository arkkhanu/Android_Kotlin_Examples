package com.example.a1_picture_and_views.ImagePicking

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a1_picture_and_views.R
import kotlinx.android.synthetic.main.activity_image_picking.*
import java.util.jar.Manifest

class image_picking : AppCompatActivity() {

    companion object {

        private val IMAGE_PICK_CODE = 1000;
        private val PERMISSION_CODE = 1001;

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_picking)

        _image_pic_btn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)
                } else {
                    pickImagefromGalary()
                }
            } else {
                pickImagefromGalary()
            }
        }
    }

    private fun pickImagefromGalary() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            _imageview.setImageURI(data?.data)

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImagefromGalary()
                } else {
                    Toast.makeText(this, "Permission Denied ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
