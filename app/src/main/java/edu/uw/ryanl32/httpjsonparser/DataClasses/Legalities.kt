package edu.uw.ryanl32.httpjsonparser.DataClasses

import java.io.Serializable

data class Legalities(
    val expanded: String,
    val standard: String,
    val unlimited: String
): Serializable