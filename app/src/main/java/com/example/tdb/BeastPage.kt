package com.example.tdb

import android.os.Bundle
import android.util.Log
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.serialization.json.Json.Default.context
import java.io.Serializable

class BeastPage:AppCompatActivity(),Serializable {
    lateinit var imageView: ImageView
    lateinit var favbuttom: ImageView
    lateinit var nameView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beastpage)
        imageView=findViewById(R.id.BeastImage)
        favbuttom=findViewById(R.id.favbuttom)
        nameView=findViewById(R.id.nameBeast)

        var beastItem: BeastItem = intent.getSerializableExtra("beast")as BeastItem
        Glide.with(this).load(beastItem.imgUrl).into(imageView)
        nameView.text = beastItem.name
        favbuttom.setOnClickListener {
            beastItem.favorite=true;
        }

    }

}