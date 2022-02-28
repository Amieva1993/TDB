package com.example.tdb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomePage : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.homepage)

            val button = findViewById<Button>(R.id.button);
            button.setOnClickListener {
                val intent = Intent(this@HomePage, AccountPage::class.java)
                startActivity(intent)
            }
        }

    }