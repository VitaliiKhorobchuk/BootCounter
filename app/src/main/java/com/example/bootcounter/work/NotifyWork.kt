package com.example.bootcounter.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.bootcounter.db.BootCounterEntity
import com.example.bootcounter.utils.DependencyHelper
import com.example.myapplication.R
import java.util.concurrent.TimeUnit

class NotifyWork(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val repository = DependencyHelper.provideBootCompletedRepository(applicationContext)
        val records = repository.getAll()

        NotificationHelper().sendNotification(applicationContext, createBootRecordsInfo(records))
        return Result.success()
    }

    private fun createBootRecordsInfo(records: List<BootCounterEntity>): String {
        var bootRecordsInfo = if (records.isNotEmpty()) {
            val timestamp = records.first().timestamp
            applicationContext.getString(R.string.notification_single_boot, timestamp)
        } else {
            applicationContext.getString(R.string.empty_boots_text)
        }
        if (records.size >= MIN_SIZE_TO_COUNT_DELTA) {
            val lastItem = records.last().timestamp
            val preLastItem = records.dropLast(1).last().timestamp
            val timeDelta = lastItem - preLastItem
            bootRecordsInfo = applicationContext.getString(R.string.notification_multiple_boots, timeDelta)
        }
        return bootRecordsInfo
    }

    companion object {
        private const val NOTIFICATION_WORK = "boot_counter_notification_work_1"
        private const val MIN_SIZE_TO_COUNT_DELTA = 2
        private const val REPEAT_INTERVAL = 15L
        private const val INIT_DELAY = 2L

        fun enqueueWork(context: Context) {
            val request = PeriodicWorkRequestBuilder<NotifyWork>(
                repeatInterval = REPEAT_INTERVAL,
                repeatIntervalTimeUnit = TimeUnit.MINUTES
            ).setInitialDelay(INIT_DELAY, TimeUnit.SECONDS)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                NOTIFICATION_WORK,
                ExistingPeriodicWorkPolicy.UPDATE,
                request
            )
        }
    }
}