package edu.uw.ryanl32.httpjsonparser

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

private const val CARD_SYNC_WORK_TAG = "CARD_SYNC_WORK_TAG"

class RefreshCardManager(context: Context) {

    private val workManager: WorkManager = WorkManager.getInstance(context)

    private fun isCardSyncRunning(): Boolean {
        return workManager.getWorkInfosByTag(CARD_SYNC_WORK_TAG).get().any {
            when(it.state) {
                WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
            }
        }
    }

    fun initCardRefresh() {
        val request = OneTimeWorkRequestBuilder<CardSyncWorker>()
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .addTag(CARD_SYNC_WORK_TAG)
            .build()

        workManager.enqueue(request)
    }

    fun startPeriodicCardRefresh() {
        if (isCardSyncRunning()) {
            return
        }

        val request = PeriodicWorkRequestBuilder<CardSyncWorker>(20, TimeUnit.MINUTES)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .addTag(CARD_SYNC_WORK_TAG)
            .build()
        workManager.enqueue(request)
    }

    fun stopPeriodicCardRefresh() {
        workManager.cancelAllWorkByTag(CARD_SYNC_WORK_TAG)
    }
}