package com.example.tdb

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ncorti.slidetoact.SlideToActView

class HomePage : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
            setContentView(R.layout.homepage)

            val enterappli = findViewById<SlideToActView>(R.id.enterappli)
            enterappli.onSlideCompleteListener =object :SlideToActView.OnSlideCompleteListener {
                override fun onSlideComplete(view: SlideToActView) {
                    this@HomePage.startActivity(Intent(this@HomePage, CategoryPage::class.java))
                }

            }
        }

    }