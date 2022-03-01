package com.example.tdb

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.appcompat.widget.Toolbar;
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.StringBuilder

class CategoryPage: AppCompatActivity() {
    private val BASE_URL = "https://fr.dofus.dofapi.fr/"
    lateinit var gridView: GridView
    lateinit var myAdapter: MyAdapter
    lateinit var beastItem: List<BeastItem>
    lateinit var toolbar: Toolbar
    lateinit var imageView: ImageView
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categorypage)
        getBeastData()
        gridView = findViewById(R.id.gridView)
        toolbar= findViewById(R.id.toolbar)
        imageView= findViewById(R.id.imagesearch)
        editText= findViewById(R.id.editText)
        imageView.setOnClickListener {
            val search = editText.toString()
            myAdapter.filter.filter(search)
        }
        setSupportActionBar(toolbar)

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



