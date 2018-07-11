package com.pguardiola.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat


internal class Notification(context: Context) {

    private lateinit var notificationBuilder: NotificationCompat.Builder
    private lateinit var notificationManager: NotificationManager
    lateinit var notification: Notification

    init {
        initialize(context)
    }

    private fun initialize(context: Context) {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(context)
        buildNotification(context)
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                    NOTIFICATION_CHANNEL, context.getString(R.string.channel_name),
                    NotificationManager.IMPORTANCE_LOW)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun buildNotification(context: Context) {
        notificationBuilder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(createPendingOpenIntent(context))
                .setOngoing(true)

        notification = notificationBuilder.build()
    }

    private fun createPendingOpenIntent(context: Context): PendingIntent {
        val pm = context.packageManager
        val intent = pm.getLaunchIntentForPackage(context.packageName)
        return PendingIntent.getActivity(context, 0, intent, 0)
    }

    companion object {
        private val NOTIFICATION_CHANNEL = "com.pguardiola.notification.NOTIFICATION_CHANNEL"
    }
}