package com.example.retrofitrecyclerview.Model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val services: BookApiInterface = retrofit.create(BookApiInterface::class.java)
}