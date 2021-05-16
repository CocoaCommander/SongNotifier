package edu.uw.ryanl32.httpjsonparser

import edu.uw.ryanl32.httpjsonparser.DataClasses.Card
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class DataRepository {

    private val cardService = Retrofit.Builder()
        .baseUrl("https://api.pokemontcg.io/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CardService::class.java)

    suspend fun getCard(query: String) = cardService.getCard(query)

}

interface CardService {
    @GET("cards")
    suspend fun getCard(
        @Query("q") q: String
    ): Card
}

// Gets all cards
//suspend fun getCard() = cardService.getCard()
//
//}
//
//interface CardService {
//    @GET("cards")
//    suspend fun getCard(): Card
//}