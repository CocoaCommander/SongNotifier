package edu.uw.ryanl32.httpjsonparser

import android.app.Application

class CardApplication: Application() {

    lateinit var dataRepository: DataRepository
    lateinit var
    val cardManager: CardManager by lazy { CardManager() }

    override fun onCreate() {
        super.onCreate()

        dataRepository = DataRepository()
    }
}