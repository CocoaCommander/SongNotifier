package edu.uw.ryanl32.httpjsonparser.DataClasses

import java.io.Serializable

data class ReverseHolofoil(
    val directLow: Double,
    val high: Double,
    val low: Double,
    val market: Double,
    val mid: Double
): Serializable