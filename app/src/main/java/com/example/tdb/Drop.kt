package com.example.tdb

import java.io.Serializable

data class Drop(
    val ankamaId: Int?,
    val dropPercent: DropPercent?,
    val imgUrl: String?,
    val name: String?,
    val url: String?
): Serializable