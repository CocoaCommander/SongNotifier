package edu.uw.ryanl32.httpjsonparser

import android.app.Application

class CardApplication: Application() {
    lateinit var cardManager: CardManager

    override fun onCreate() {
        super.onCreate()

        this.cardManager = CardManager()
    }
}