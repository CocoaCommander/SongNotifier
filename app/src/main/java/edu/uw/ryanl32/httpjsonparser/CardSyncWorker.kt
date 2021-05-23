package edu.uw.ryanl32.httpjsonparser

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class CardSyncWorker(
    private val context: Context,
    workerParameters: WorkerParameters
):CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {

        return Result.success()
    }
}