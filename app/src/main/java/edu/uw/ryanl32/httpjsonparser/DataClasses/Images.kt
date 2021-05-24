package edu.uw.ryanl32.httpjsonparser.DataClasses

import java.io.Serializable

data class Images(
    val large: String,
    val small: String
): Serializable