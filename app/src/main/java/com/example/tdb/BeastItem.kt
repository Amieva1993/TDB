package com.example.tdb

data class BeastItem(
    val id: Int,
    val ankamaId: Int,
    val areas: List<String>,
    val drops: List<Drop>,
    val imgUrl: String,
    val level: Any,
    val name: String,
    val resistances: List<Resistance>,
    val statistics: List<Statistic>,
    val type: String,
    val url: String
)