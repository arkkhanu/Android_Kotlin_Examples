package com.example.retrofitapplication

import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CompanyAdaptor(val data:ArrayList<CompanyData.CompanyItems>):RecyclerView.Adapter<CompanyAdaptor.CompanyHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): CompanyHolder =
        CompanyHolder(LayoutInflater.from(parent.context).inflate(R.layout.joblistrec,parent,false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CompanyHolder, position: Int) = holder.bindItem(data[position])

    class CompanyHolder(v: View):RecyclerView.ViewHolder(v){

        val companylogo_joblist = itemView.findViewById<ImageView>(R.id.joblist_company_logo)
        val company_joblist = itemView.findViewById<TextView>(R.id.joblist_company)
        val title_joblist = itemView.findViewById<TextView>(R.id.joblist_title)
        val created_at_joblist = itemView.findViewById<TextView>(R.id.joblist_created_at)
        val type_joblist = itemView.findViewById<TextView>(R.id.joblist_type)
        fun bindItem(tempdata:CompanyData.CompanyItems)
        {
            company_joblist.text = tempdata.company
            title_joblist.text = tempdata.title
            created_at_joblist.text = tempdata.created_at
            type_joblist.text = tempdata.type

            Glide.with(itemView.context).applyDefaultRequestOptions(RequestOptions().apply{
                placeholder(CircularProgressDrawable(itemView.context).apply {
                    strokeWidth = 2f
                    centerRadius = 50f
                    start()
                })
            }).load(tempdata.company_logo).into(companylogo_joblist)
        }
    }
}
