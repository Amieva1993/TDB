package com.example.tdb

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FilterPage:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filter)

        val buttonFilter = findViewById<Button>(R.id.button)

    }
}