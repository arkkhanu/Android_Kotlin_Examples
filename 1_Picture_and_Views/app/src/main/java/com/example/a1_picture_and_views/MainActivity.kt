package com.example.a1_picture_and_views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a1_picture_and_views.ImagePicking.image_picking
import com.example.a1_picture_and_views.ListView.listView
import com.example.a1_picture_and_views.TakingPicture.takingpicture
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_1.setOnClickListener{
            val intent =Intent(this@MainActivity, listView::class.java)
            startActivity(intent)
        }
        btn_pick_image.setOnClickListener{
            val intent = Intent(this,image_picking::class.java)
            startActivity(intent)
        }

        btn_capture_image.setOnClickListener{
            val intent = Intent(this,takingpicture::class.java)
            startActivity(intent)
        }

    }
}
