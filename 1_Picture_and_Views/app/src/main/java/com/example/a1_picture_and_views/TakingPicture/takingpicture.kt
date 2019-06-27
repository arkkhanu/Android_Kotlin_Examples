package com.example.a1_picture_and_views.TakingPicture

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Gravity
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.a1_picture_and_views.R
import kotlinx.android.synthetic.main.activity_takingpicture.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class takingpicture : AppCompatActivity() {

    companion object {
        val IMAGE_REQUEST_CODE = 101;
        val REQUEST_PERMISSION_CODE = 100;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_takingpicture)

        capture_btn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, REQUEST_PERMISSION_CODE)
                } else {
                    imageCapture()
                }
            } else {
                imageCapture()
            }
        }
    }

/*
//        Here a simple image is capture without saving it in internal storage
    private fun imageCapture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, IMAGE_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_REQUEST_CODE){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            image_viewing.setImageBitmap(imageBitmap)
        }
    }

*/



    @Throws(IOException::class)
    private fun createImageFile():File{
        val timeStamp : String = SimpleDateFormat("yyyyMMdd-HHmmss").format(Date())
        val storageDir: File? =  getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    private fun imageCapture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photofile:File? = try{
                    createImageFile()
                }
                catch (ex:IOException){null}
                photofile?.also {
                    val photoURI = FileProvider.getUriForFile(this,"com.example.a1_picture_and_views",it)
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI)
                    startActivityForResult(takePictureIntent, IMAGE_REQUEST_CODE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode== IMAGE_REQUEST_CODE){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            image_viewing.setImageBitmap(imageBitmap)

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    imageCapture()
                } else {
                    Toast.makeText(this, "Permission Denied ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}
