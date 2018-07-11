package com.pguardiola.notification

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity

class ActivityOne : AppCompatActivity() {

    private lateinit var button: FloatingActionButton
    private lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        button = findViewById(R.id.stopService)
        serviceIntent = createServiceIntent()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            applicationContext.startForegroundService(serviceIntent)
        } else {
            applicationContext.startService(serviceIntent)
        }
        button.setOnClickListener {
            applicationContext.stopService(serviceIntent)
        }
    }

    private fun createServiceIntent(): Intent {
        return Intent(applicationContext, ForegroundService::class.java)
    }
}
