package com.example.tdb

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FavPage:AppCompatActivity() {

    private val BASE_URL = "https://fr.dofus.dofapi.fr/"
    lateinit var gridViewFav: GridView
    lateinit var myAdapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myAdapter = MyAdapter(this, ArrayList<BeastItem>())
        for (beast in myAdapter.beastFiltredList){
            if (beast.favorite){
                gridViewFav.adapter = myAdapter
            }
        }
        myAdapter.notifyDataSetChanged()
        gridViewFav = GridView(this)
        setContentView(R.layout.favorispage)

        gridViewFav = findViewById(R.id.gridViewFav)

    }

    private fun getBeastData() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val service = retrofit.create(ApiInterface::class.java)
        val retrofitData = service.getData()

        retrofitData.enqueue(object : Callback<List<BeastItem>>
        {

            override fun onResponse(call: Call<List<BeastItem>>, response: Response<List<BeastItem>>)
            {
                val responseBody = response.body()!!
                myAdapter = MyAdapter(baseContext,responseBody)
                myAdapter.notifyDataSetChanged()
                gridViewFav.adapter = myAdapter

            }

            override fun onFailure(call: Call<List<BeastItem>>, t: Throwable)
            {
                Log.d("MainActivity", "onFailure" + t.message)
            }

        })
    }
}