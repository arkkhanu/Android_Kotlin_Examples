package com.example.retrofitapplication

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface CompanyInter {

    @GET("/positions.json?")
    fun getListing(@Query("description") description: String, @Query("location") location: String): retrofit2.Call<CompanyData>

}