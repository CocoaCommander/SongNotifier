package edu.uw.ryanl32.httpjsonparser

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlin.random.Random

class CardSyncWorker(
    private val context: Context,
    workerParameters: WorkerParameters
):CoroutineWorker(context, workerParameters) {

    private val application by lazy { context.applicationContext as CardApplication }
    private val cardNotificationManager by lazy { application.notificationManager }
    private val dataRepository by lazy { application.dataRepository }
    private val cardManager by lazy { application.cardManager }

    override suspend fun doWork(): Result {

        Log.i("CardSyncWorker", "Syncing cards now")

        val dexNum = Random.nextInt(0, 899)
        val cardSelection = dataRepository.getCard("nationalPokedexNumbers:[${dexNum} TO ${dexNum}]")
        val currCard = cardSelection.data[Random.nextInt(0, cardSelection.data.size)]
        cardManager.setCard(currCard)
        cardNotificationManager.publishNewCardNotification(currCard)
        return Result.success()
    }
}