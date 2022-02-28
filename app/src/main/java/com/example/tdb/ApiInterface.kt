package com.example.tdb


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryName

interface ApiInterface {
    @GET("monsters")
    fun getData(): Call<List<BeastItem>>
}