package com.example.retrofitrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitrecyclerview.Model.Api
import com.example.retrofitrecyclerview.Model.BookAdaptor
import com.example.retrofitrecyclerview.Model.VolumesResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity(), Callback<VolumesResponse> {

    var bookdata: ArrayList<VolumesResponse.VolumeItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = BookAdaptor(bookdata)
        recycler_view.layoutManager = LinearLayoutManager(this)

        btn_search.setOnClickListener {
            if (et_search.text.toString().isEmpty())
            {
                et_search.error = "Can't be empty"
            }
            else{
                Api().services.getbookParamet(et_search.text.toString()).enqueue(this)
            }
        }
    }

    override fun onFailure(call: Call<VolumesResponse>, t: Throwable) {
        Toast.makeText(this,"Network Error ${bookdata.size}",Toast.LENGTH_SHORT).show()
        if(t is IOException)
            Toast.makeText(this,"Actual Network Error  ${bookdata.size}",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this,"Logical error  ${bookdata.size}",Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<VolumesResponse>, response: Response<VolumesResponse>) {
        val temp_data = response.body()
        temp_data?.also { temp->
            temp.items?.also {
                bookdata.addAll(it.toList())
                recycler_view.adapter?.notifyDataSetChanged()
                Toast.makeText(this,"Done ${bookdata.size}",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
