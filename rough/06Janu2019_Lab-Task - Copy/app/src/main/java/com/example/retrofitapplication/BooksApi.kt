package com.example.retrofitapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://www.googleapis.com/books/v1/volumes?q=kotlin

interface BooksApi {

    @GET("books/v1/volumes")
    fun getVolumes(@Query("q") q:String ):retrofit2.Call<VolumesResponse>

//    @GET("books/v1/volumes")
//    fun getVol(@Query("q") q:String ):Call<VolumesResponse>

}