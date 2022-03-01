package com.example.tdb

import java.io.Serializable

data class Resistance(
    val Air: Air?,
    val Eau: Eau?,
    val Feu: Feu?,
    val Neutre: Neutre?,
    val Terre: Terre?
): Serializable