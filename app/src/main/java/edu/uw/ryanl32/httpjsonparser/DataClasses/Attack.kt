package edu.uw.ryanl32.httpjsonparser.DataClasses

import java.io.Serializable

data class Attack(
    val convertedEnergyCost: Int,
    val cost: List<String>,
    val damage: String,
    val name: String,
    val text: String
): Serializable