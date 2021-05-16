package edu.uw.ryanl32.httpjsonparser

import edu.uw.ryanl32.httpjsonparser.DataClasses.Card
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class DataRepository {

    private val cardService = Retrofit.Builder()
        .baseUrl("https://api.pokemontcg.io/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CardService::class.java)

    suspend fun getCard(num: Int) = cardService.getCard(num)

}

interface CardService {
    @GET("cards?q=nationalPokedexNumbers:[{num}%20TO%20{num}]")
    suspend fun getCard(
        @Path("num") num: Int
    ): Card
}