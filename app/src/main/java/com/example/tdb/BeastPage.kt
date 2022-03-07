package com.example.tdb

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.marginLeft
import androidx.core.view.marginStart
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
    lateinit var favbuttom: ImageView
    lateinit var nameView: TextView
    lateinit var typeView: TextView
    lateinit var idView: TextView

    lateinit var statsView: CardView
    lateinit var valuePV: TextView
    lateinit var valuePA: TextView
    lateinit var valuePM: TextView

    lateinit var resView: CardView
    lateinit var valueTerre: TextView
    lateinit var valueAir: TextView
    lateinit var valueFeu: TextView
    lateinit var valueEau: TextView
    lateinit var valueNeutre: TextView

    lateinit var areasView: CardView


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beastpage)
        imageView=findViewById(R.id.BeastImage)
        favbuttom=findViewById(R.id.favbuttom)
        nameView=findViewById(R.id.nameBeast)
        typeView=findViewById(R.id.typeView)
        idView=findViewById(R.id.idView)

        statsView=findViewById(R.id.statsView)
        valuePV=findViewById(R.id.valuePV)
        valuePA=findViewById(R.id.valuePA)
        valuePM=findViewById(R.id.valuePM)

        resView=findViewById(R.id.resView)
        valueTerre=findViewById(R.id.valueTerre)
        valueAir=findViewById(R.id.valueAir)
        valueFeu=findViewById(R.id.valueFeu)
        valueEau=findViewById(R.id.valueEau)
        valueNeutre=findViewById(R.id.valueNeutre)

        areasView=findViewById(R.id.areasView)


        var beastItem: BeastItem = intent.getSerializableExtra("beast")as BeastItem
        Glide.with(this).load(beastItem.imgUrl).into(imageView)
        nameView.text = beastItem.name
        typeView.text = beastItem.type
        idView.text = "#" + beastItem._id.toString()

        // au chargement de la page
        statsView.visibility = View.VISIBLE
        resView.visibility = View.INVISIBLE
        areasView.visibility = View.INVISIBLE

        // Statistiques
        var minPV = beastItem.statistics?.get(0)?.PV?.min
        var maxPV = beastItem.statistics?.get(0)?.PV?.max
        var minPA = beastItem.statistics?.get(1)?.PA?.min
        var maxPA = beastItem.statistics?.get(1)?.PA?.max
        var minPM = beastItem.statistics?.get(2)?.PM?.min
        var maxPM = beastItem.statistics?.get(2)?.PM?.max

        if (minPV == null) minPV = "Aucune donnée"
        if (maxPV == null) maxPV = "Aucune donnée"
        if (minPA == null) minPA = "Aucune donnée"
        if (maxPA == null) maxPA = "Aucune donnée"
        if (minPM == null) minPM = "Aucune donnée"
        if (maxPM == null) maxPM = "Aucune donnée"

        valuePV.text = minPV + " / " + maxPV
        valuePA.text = minPA + " / " + maxPA
        valuePM.text = minPM + " / " + maxPM


        // Résistances
        var minTerre = beastItem.resistances?.get(0)?.Terre?.min
        var maxTerre = beastItem.resistances?.get(0)?.Terre?.max
        var minAir = beastItem.resistances?.get(1)?.Air?.min
        var maxAir = beastItem.resistances?.get(1)?.Air?.max
        var minFeu = beastItem.resistances?.get(2)?.Feu?.min
        var maxFeu = beastItem.resistances?.get(2)?.Feu?.max
        var minEau = beastItem.resistances?.get(3)?.Eau?.min
        var maxEau = beastItem.resistances?.get(3)?.Eau?.max
        var minNeutre = beastItem.resistances?.get(4)?.Neutre?.min
        var maxNeutre = beastItem.resistances?.get(4)?.Neutre?.max

        if (minTerre == null) minTerre = "Aucune donnée"
        if (maxTerre == null) maxTerre = "Aucune donnée"
        if (minAir == null) minAir = "Aucune donnée"
        if (maxAir == null) maxAir = "Aucune donnée"
        if (minFeu == null) minFeu = "Aucune donnée"
        if (maxFeu == null) maxFeu = "Aucune donnée"
        if (minEau == null) minEau = "Aucune donnée"
        if (maxEau == null) maxEau = "Aucune donnée"
        if (minNeutre == null) minNeutre = "Aucune donnée"
        if (maxNeutre == null) maxNeutre = "Aucune donnée"

        valueTerre.text = minTerre + " / " + maxTerre
        valueAir.text = minAir + " / " + maxAir
        valueFeu.text = minFeu + " / " + maxFeu
        valueEau.text = minEau + " / " + maxEau
        valueNeutre.text = minNeutre + " /  " + maxNeutre


        // Areas
        // TODO : ajouter une margin à gauche (20dp) et une margin entre chaque textview généré
        var textView : TextView? = null
        var layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        for (area in beastItem.areas!!){
            textView = TextView(this)
            textView.text = "- " + area
            textView.setTextColor(Color.parseColor("#774A3D"))
            textView.textSize = 20F
            textView.layoutParams = layoutParams

            areasView.addView(textView)
        }


        // Drops
        // TODO : générer une cardview pour chaque drop avec en affichage son image, son nom et le pourcentage de drop


        // Pour les sélection de layout sans rechargement de la page
        var tabLayout = findViewById<TabLayout>(R.id.fragChoice)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        statsView.visibility = View.VISIBLE
                        resView.visibility = View.INVISIBLE
                        areasView.visibility = View.INVISIBLE
                    }
                    1 -> {
                        statsView.visibility = View.INVISIBLE
                        resView.visibility = View.VISIBLE
                        areasView.visibility = View.INVISIBLE
                    }
                    2 -> {
                        statsView.visibility = View.INVISIBLE
                        resView.visibility = View.INVISIBLE
                        areasView.visibility = View.VISIBLE
                    }
                    3 -> {
                        statsView.visibility = View.INVISIBLE
                        resView.visibility = View.INVISIBLE
                        areasView.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

}