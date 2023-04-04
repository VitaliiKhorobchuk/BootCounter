package com.example.bootcounter.broadcast

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.bootcounter.db.BootCounterEntity
import com.example.bootcounter.ui.MainActivity
import com.example.bootcounter.utils.DependencyHelper
import com.example.bootcounter.work.NotificationHelper
import com.example.bootcounter.work.NotifyWork
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Add dependency injection
class BootCompletedReceiver : BroadcastReceiver() {

    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("123TAG", "BootCompletedReceiver onReceive: received something ")

        if (intent != null && intent.action != null) {
            val action = intent.action

            if (action == Intent.ACTION_BOOT_COMPLETED) {
                GlobalScope.launch {
                    context?.let {
                        val repository = DependencyHelper.provideBootCompletedRepository(it)
                        repository.insert(BootCounterEntity(System.currentTimeMillis()))
                        updateNotification(it)
                    }
                }
            }
        }
    }

    private fun updateNotification(context: Context) {
        if (NotificationHelper.isNotificationVisible(context)) {
            NotifyWork.enqueueWork(context)
        }
    }
}