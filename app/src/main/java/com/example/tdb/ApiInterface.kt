package com.example.tdb


import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("monsters")
    fun getData(): Call<List<BeastItem>>
}