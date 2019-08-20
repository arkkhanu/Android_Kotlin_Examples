package com.example.retrofitapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.filterlayout.*
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity(),retrofit2.Callback<CompanyData> {


    var companydata : ArrayList<CompanyData.CompanyItems> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        company_recycler_rc.adapter = CompanyAdaptor(companydata)
        company_recycler_rc.layoutManager = LinearLayoutManager(this)

        btn_click.setOnClickListener {

            Api().services.getListing("java","london").enqueue(this)
       }

    }

    override fun onFailure(call: Call<CompanyData>, t: Throwable) {
//        Toast.makeText(this, "Network Error_ ", Toast.LENGTH_SHORT).show()
        if (t is IOException) {
            Toast.makeText(this, "this is an actual network failure :( inform the user and possibly retry"
                , Toast.LENGTH_SHORT).show()
            // logging probably not necessary
        }
        else {
            Toast.makeText(this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show()
            // todo log to some central bug tracking service
        }
    }

    override fun onResponse(call: Call<CompanyData>, response: Response<CompanyData>) {

     /*   val tempo_data = response.body()
        tempo_data.also { temp->
            temp?.item?.also {
                Toast.makeText(this@MainActivity,"ok" , Toast.LENGTH_SHORT).show()
                companydata.addAll(it.toList())
                company_recycler_rc.adapter?.notifyDataSetChanged()
            }
        }*/

       val volumes = response.body()
        volumes.also { vol->
            vol?.item?.also {
                companydata.addAll(it.toList())
                Toast.makeText(this@MainActivity,"ok" , Toast.LENGTH_SHORT).show()
                company_recycler_rc.adapter?.notifyDataSetChanged()
            }
        }

    }

}
