package edu.uw.ryanl32.httpjsonparser.DataClasses

import java.io.Serializable

data class Resistance(
    val type: String,
    val value: String
): Serializable