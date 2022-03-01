package com.example.tdb

import android.os.Bundle
import android.util.Log
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent


class BeastPage:AppCompatActivity() {
    lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beastcategorypage)
        gridView = findViewById(R.id.gridview2)
        //var beastItem: Modal = itent.getSerializableExtra("beast")as Modal
    }

}