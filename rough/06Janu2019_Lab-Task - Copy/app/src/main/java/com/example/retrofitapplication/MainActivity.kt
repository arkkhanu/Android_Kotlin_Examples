package com.example.retrofitapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), retrofit2.Callback<VolumesResponse> {


    var booksData: ArrayList<VolumesResponse.VolumeItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        booksList.adapter = BooksAdapter(booksData)
        booksList.layoutManager = LinearLayoutManager(this)

        apiBtn.setOnClickListener {
            Api().services.getVolumes("kotlin").enqueue(this)

        }
    }

    override fun onFailure(call: Call<VolumesResponse>, t: Throwable) {
        Toast.makeText(this, "Network Error ", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<VolumesResponse>, response: Response<VolumesResponse>) {

        val myVolumes = response.body()
        myVolumes.also{ vol->
            vol?.items?.also {
                Toast.makeText(this@MainActivity,"ok" , Toast.LENGTH_SHORT).show()
                booksData.addAll(it.toList())
                booksList.adapter?.notifyDataSetChanged()
            }
        }
    }
}
