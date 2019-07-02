package com.example.retrofitrecyclerview.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.retrofitrecyclerview.R

class BookAdaptor(val data:ArrayList<VolumesResponse.VolumeItem>): RecyclerView.Adapter<BookAdaptor.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_of_recycler,parent,false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) =
            holder.bindItem(data[position])

    class BookViewHolder(view:View):RecyclerView.ViewHolder(view){

        val title = itemView.findViewById<TextView>(R.id.title_tv)
        val author = itemView.findViewById<TextView>(R.id.author_tv)
        val publisher = itemView.findViewById<TextView>(R.id.publisherdate_tv)
        val bookimage = itemView.findViewById<ImageView>(R.id.thumbnail_book)
        fun bindItem(tempdata:VolumesResponse.VolumeItem) {
            title.text = tempdata.volumeInfo?.title
            author.text = tempdata.volumeInfo?.authors?.joinToString()
            publisher.text = tempdata.volumeInfo?.publisher

            Glide.with(itemView.context).applyDefaultRequestOptions(RequestOptions().apply{
                placeholder(CircularProgressDrawable(itemView.context).apply {
                    strokeWidth = 2f
                    centerRadius = 50f
                    start()
                })
            }).load(tempdata.volumeInfo?.imageLinks?.thumbnail).into(bookimage)

        }
    }
}