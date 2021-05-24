package edu.uw.ryanl32.httpjsonparser

import edu.uw.ryanl32.httpjsonparser.DataClasses.Data

class CardManager {
    var currCard: Data? = null
        private set
    fun setCard(card: Data) {
        currCard = card
    }
}
