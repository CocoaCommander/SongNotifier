package edu.uw.ryanl32.httpjsonparser.DataClasses

data class Prices(
    val `1stEditionNormal`: StEditionNormal,
    val holofoil: Holofoil,
    val normal: Normal,
    val reverseHolofoil: ReverseHolofoil
)