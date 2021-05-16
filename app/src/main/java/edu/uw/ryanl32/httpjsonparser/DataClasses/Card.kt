package edu.uw.ryanl32.httpjsonparser.DataClasses

data class Card(
    val count: Int,
    val data: List<Data>,
    val page: Int,
    val pageSize: Int,
    val totalCount: Int
)