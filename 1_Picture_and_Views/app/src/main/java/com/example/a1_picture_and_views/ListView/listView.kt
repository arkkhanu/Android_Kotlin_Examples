package com.example.a1_picture_and_views.ListView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.a1_picture_and_views.R
import kotlinx.android.synthetic.main.activity_list_view.*

class listView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        var itemlist:ArrayList<String> = ArrayList()
        itemlist.add("aq")
        itemlist.add("bw")
        itemlist.add("ce")
        itemlist.add("dr")
        itemlist.add("et")
        itemlist.add("fy")
        itemlist.add("aq")
        itemlist.add("bw")
        itemlist.add("ce")
        itemlist.add("dr")
        itemlist.add("et")
        itemlist.add("fy")
        itemlist.add("aq")
        itemlist.add("bw")
        itemlist.add("ce")
        itemlist.add("dr")
        itemlist.add("et")
        itemlist.add("fy")

        val adaptor = ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,itemlist)
        id_listview.adapter=adaptor;

        id_listview.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this,"Item Clicked $l  :  $i",Toast.LENGTH_SHORT ).show()
        }

    }
}
