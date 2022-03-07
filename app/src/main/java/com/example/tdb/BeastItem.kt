package com.example.tdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class BeastItem(
    val  _id: Int?,
    val ankamaId: Int?,
    val name: String,
    val level: Int?,
    val type: String,
    val imgUrl: String?,
    val url: String?,
    val statistics: List<Statistic>?,
    val resistances: List<Resistance>?,
    val areas: List<String>?,
    val drops: List<Drop>?,
    var favorite:Boolean,
):Serializable
