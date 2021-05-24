package edu.uw.ryanl32.httpjsonparser.DataClasses

import java.io.Serializable

data class Normal(
    val directLow: Any,
    val high: Double,
    val low: Double,
    val market: Double,
    val mid: Double
): Serializable