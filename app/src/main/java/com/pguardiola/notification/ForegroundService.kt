package com.pguardiola.notification

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder


class ForegroundService : Service() {

    private val localBinder = LocalBinder()

    override fun onBind(intent: Intent?): IBinder {
        return localBinder
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val notification = Notification(this.applicationContext).notification
        startForeground(NOTIFICATION_ID, notification)
        return START_STICKY
    }

    companion object {
        private val NOTIFICATION_ID = 31337
    }

    internal inner class LocalBinder : Binder()
}