package io.github.kunal26das.radius.database.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import io.github.kunal26das.radius.database.FeatureDatabase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

@HiltWorker
class ClearDatabaseWorker @AssistedInject internal constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val featureDatabase: FeatureDatabase,
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        featureDatabase.clearAllTables()
        return Result.success()
    }

    companion object {
        fun enqueue(context: Context) {
            val periodicWorkRequest = PeriodicWorkRequest.Builder(
                ClearDatabaseWorker::class.java, 1, TimeUnit.DAYS
            ).build()
            WorkManager.getInstance(context).enqueue(periodicWorkRequest)
        }
    }
}