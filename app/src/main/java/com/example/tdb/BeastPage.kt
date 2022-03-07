package com.example.tdb

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.marginLeft
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
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
        var PV = ""
        var PA = ""
        var PM = ""

        if (maxPV != null && minPV != null) PV = "$minPV à $maxPV"
        if (maxPV == null && minPV != null) PV = minPV
        if (minPV == null && maxPV != null) PV = maxPV
        if (maxPV == null && minPV == null) PV = "Aucune donnée"

        if (maxPA != null && minPA != null) PA = "$minPA à $maxPA"
        if (maxPA == null && minPA != null) PA = minPA
        if (minPA == null && maxPA != null) PA = maxPA
        if (maxPA == null && minPA == null) PA = "Aucune donnée"

        if (maxPM != null && minPM != null) PM = "$minPM à $maxPM"
        if (maxPM == null && minPM != null) PM = minPM
        if (minPM == null && maxPM != null) PM = maxPM
        if (maxPM == null && minPM == null) PM = "Aucune donnée"

        valuePV.text = PV
        valuePA.text = PA
        valuePM.text = PM


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

        var terre = ""
        var air = ""
        var feu = ""
        var eau = ""
        var neutre = ""

        if (minTerre == null && maxTerre == null) terre = "Aucune donnée"
        if (minTerre != null && maxTerre == null) terre = minTerre
        if (minTerre == null && maxTerre != null) terre = maxTerre
        if (minTerre != null && maxTerre != null) terre = "$minTerre à $maxTerre"

        if (minAir == null && maxAir == null) air = "Aucune donnée"
        if (minAir != null && maxAir == null) air = minAir
        if (minAir == null && maxAir != null) air = maxAir
        if (minAir != null && maxAir != null) air = "$minAir à $maxAir"

        if (minFeu == null && maxFeu == null) feu = "Aucune donnée"
        if (minFeu != null && maxFeu == null) feu = minFeu
        if (minFeu == null && maxFeu != null) feu = maxFeu
        if (minFeu != null && maxFeu != null) feu = "$minFeu à $maxFeu"

        if (minEau == null && maxEau == null) eau = "Aucune donnée"
        if (minEau != null && maxEau == null) eau = minEau
        if (minEau == null && maxEau != null) eau = maxEau
        if (minEau != null && maxEau != null) eau = "$minEau à $maxEau"

        if (minNeutre == null && maxNeutre == null) neutre = "Aucune donnée"
        if (minNeutre != null && maxNeutre == null) neutre = minNeutre
        if (minNeutre == null && maxNeutre != null) neutre = maxNeutre
        if (minNeutre != null && maxNeutre != null) neutre = "$minNeutre à $maxNeutre"

        valueTerre.text = terre
        valueAir.text = air
        valueFeu.text = feu
        valueEau.text = eau
        valueNeutre.text = neutre


        // Areas
        var areasTextView : TextView? = null
        var areasLayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        areasTextView = TextView(this)
        areasTextView.setTextColor(Color.parseColor("#774A3D"))
        areasTextView.textSize = 20F
        areasTextView.layoutParams = areasLayoutParams

        var listAreas = ""
        for (area in beastItem.areas!!){
            listAreas += "\t\t\t- " + area + "\n"
        }

        areasTextView.text = "\n" + listAreas
        areasView.addView(areasTextView)


        // Drops
        var gridView = findViewById<GridView>(R.id.gridView)
        var dropAdapter = DropAdapter(baseContext, beastItem.drops!!)
        dropAdapter.notifyDataSetChanged()
        gridView.adapter = dropAdapter


        // Pour les sélection de layout sans rechargement de la page
        var tabLayout = findViewById<TabLayout>(R.id.fragChoice)
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
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