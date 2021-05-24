package edu.uw.ryanl32.httpjsonparser.DataClasses

import java.io.Serializable

data class Tcgplayer(
    val prices: Prices,
    val updatedAt: String,
    val url: String
): Serializable