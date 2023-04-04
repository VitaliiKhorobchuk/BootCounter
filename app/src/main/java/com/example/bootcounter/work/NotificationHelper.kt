package com.example.bootcounter.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.bootcounter.ui.MainActivity
import com.example.myapplication.R

class NotificationHelper {

    fun sendNotification(applicationContext: Context, notificationSubtitle: String) {

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val titleNotification = applicationContext.getString(R.string.notification_title)
        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(titleNotification)
            .setContentText(notificationSubtitle)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(false)

        notification.priority = NotificationCompat.PRIORITY_MAX

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification.setChannelId(NOTIFICATION_CHANNEL)
            val channel =
                NotificationChannel(NOTIFICATION_CHANNEL, NOTIFICATION_NAME, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(NOTIFICATION_ID, notification.build())
    }

    companion object {
        const val NOTIFICATION_ID = 1111
        const val NOTIFICATION_NAME = "boot_counter"
        const val NOTIFICATION_CHANNEL = "boot_counter_channel_01"

        fun isNotificationVisible(context: Context): Boolean {
            val notificationIntent = Intent(context, MainActivity::class.java)
            val test = PendingIntent.getActivity(context, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
            return test != null
        }
    }
}