package com.example.tdb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ncorti.slidetoact.SlideToActView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchPage:AppCompatActivity() {
    private val BASE_URL = "https://fr.dofus.dofapi.fr/"
    lateinit var gridView: GridView
    lateinit var myAdapter: MyAdapter
    lateinit var imageView: ImageView
    lateinit var editText: EditText
    lateinit var progressbar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressbar = ProgressBar(this)
        gridView = GridView(this)
        progressbar.visibility = View.VISIBLE
        gridView.visibility = View.GONE
        setContentView(R.layout.searchpage)
        getBeastData()
        myAdapter = MyAdapter(this, ArrayList<BeastItem>())
        gridView = findViewById(R.id.gridView)
        progressbar = findViewById(R.id.progressbar)

        imageView = findViewById(R.id.imagesearch)
        editText = findViewById(R.id.editText)
        imageView.setOnClickListener {
            val search = editText.text.toString()
            myAdapter.filter.filter(search)
        }
        val buttonSearch = findViewById<Button>(R.id.buttonSearch)
        val buttonMonster = findViewById<Button>(R.id.buttonMonster)
        val buttonFav = findViewById<Button>(R.id.buttonFav)
        buttonSearch.setOnClickListener {
            this.startActivity(Intent(this, SearchPage::class.java))
        }
        buttonMonster.setOnClickListener {
            this.startActivity(Intent(this, CategoryPage::class.java))
        }
        buttonFav.setOnClickListener {
            this.startActivity(Intent(this, FavPage::class.java))
        }

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
                progressbar.visibility=View.GONE
                gridView.visibility=View.VISIBLE
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