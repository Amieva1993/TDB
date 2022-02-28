package com.example.tdb

data class BeastItem(
    val _id: Int?,
    val ankamaId: Int?,
    val name: String?,
    val level: Int?,
    val type: String?,
    val imgUrl: String?,
    val url: String?,
    val statistics: List<Statistic>?,
    val resistances: List<Resistance>?,
    val areas: List<String>?,
    val drops: List<Drop>?,
)