package edu.uw.ryanl32.httpjsonparser

import edu.uw.ryanl32.httpjsonparser.DataClasses.Card

class CardManager {
    var currCard: Card? = null
        private set
    fun setCard(card: Card) {
        currCard = card
    }
}
