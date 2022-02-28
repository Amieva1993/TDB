package com.example.tdb

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.StringBuilder

class AccountPage: AppCompatActivity() {
    private val BASE_URL = "https://fr.dofus.dofapi.fr/"
    lateinit var gridView: GridView
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accountpage)
        getBeastData()
        gridView = findViewById(R.id.gridView)

    }

    private fun getBeastData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(ApiInterface::class.java)
        val retrofitData = service.getData()

        retrofitData.enqueue(object : Callback<List<BeastItem>>
        {

            override fun onResponse(call: Call<List<BeastItem>>,response: Response<List<BeastItem>>)
            {
                val responseBody = response.body()!!
                for(monster in responseBody){
                    println(monster.name)
                }
                myAdapter = MyAdapter(baseContext,responseBody)
                myAdapter.notifyDataSetChanged()
                gridView.adapter = myAdapter
            }

            override fun onFailure(call: Call<List<BeastItem>>, t: Throwable)
            {
                Log.d("MainActivity", "onFailure" + t.message)
            }
        })
    }
}



