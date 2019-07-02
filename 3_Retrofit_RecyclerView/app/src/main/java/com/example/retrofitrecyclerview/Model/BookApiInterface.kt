package com.example.retrofitrecyclerview.Model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiInterface {
    @GET("books/v1/volumes")
    fun getbookParamet(@Query("q") q:String) : Call<VolumesResponse>

//    https://www.googleapis.com/books/v1/volumes?q=java

}