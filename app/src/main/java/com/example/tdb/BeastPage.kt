package com.example.tdb

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.inflate
import kotlinx.serialization.json.Json.Default.context
import org.w3c.dom.Text
import java.io.Serializable

class BeastPage:AppCompatActivity(),Serializable {
    lateinit var imageView: ImageView
    lateinit var nameView: TextView
    lateinit var typeView: TextView
    lateinit var idView: TextView

    lateinit var labelPV: TextView
    lateinit var labelPA: TextView
    lateinit var labelPM: TextView
    lateinit var valuePV: TextView
    lateinit var valuePA: TextView
    lateinit var valuePM: TextView


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beastpage)
        imageView=findViewById(R.id.BeastImage)
        nameView=findViewById(R.id.nameBeast)
        typeView=findViewById(R.id.typeView)
        idView=findViewById(R.id.idView)

        labelPV=findViewById(R.id.labelPV)
        labelPA=findViewById(R.id.labelPA)
        labelPM=findViewById(R.id.labelPM)
        valuePV=findViewById(R.id.valuePV)
        valuePA=findViewById(R.id.valuePA)
        valuePM=findViewById(R.id.valuePM)


        var beastItem: BeastItem = intent.getSerializableExtra("beast")as BeastItem
        Glide.with(this).load(beastItem.imgUrl).into(imageView)
        nameView.text = beastItem.name
        typeView.text = beastItem.type
        idView.text = "#" + beastItem._id.toString()

        // à l'ouverture de la page
//        labelPV.visibility = View.VISIBLE
//        labelPA.visibility = View.VISIBLE
//        labelPM.visibility = View.VISIBLE
//        valuePV.visibility = View.VISIBLE
//        valuePA.visibility = View.VISIBLE
//        valuePM.visibility = View.VISIBLE

//        valuePV.text = beastItem.statistics?.get(0)?.PV?.min.toString() + " - " + beastItem.statistics?.get(0)?.PV?.max.toString()
//        valuePA.text = beastItem.statistics?.get(0)?.PA?.min.toString() + " - " + beastItem.statistics?.get(0)?.PA?.max.toString()
//        valuePM.text = beastItem.statistics?.get(0)?.PM?.min.toString() + " - " + beastItem.statistics?.get(0)?.PM?.max.toString()

        // Pour les sélection de layout sans rechargement de la page
        var tabLayout = findViewById<TabLayout>(R.id.fragChoice)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> println("stats")
                    1 -> println("resistances")
                    2 -> println("areas")
                    3 -> println("drops")
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

}