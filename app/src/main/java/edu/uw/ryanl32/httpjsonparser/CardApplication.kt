package edu.uw.ryanl32.httpjsonparser

import android.app.Application

class CardApplication: Application() {

    lateinit var dataRepository: DataRepository
    lateinit var notificationManager: CardNotificationManager
    lateinit var refreshCardManager: RefreshCardManager
    val cardManager: CardManager by lazy { CardManager() }

    override fun onCreate() {
        super.onCreate()

        this.notificationManager = CardNotificationManager(this)
        this.refreshCardManager = RefreshCardManager(this)
        this.dataRepository = DataRepository()
    }
}