package com.juarez.androidbroadcastreceiver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    init {
        Log.d("MyService", "service running...")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("MyService", "onCreate...")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val extraString = intent?.getStringExtra("EXTRA_MESSAGE")

        extraString?.let {
            Log.d("MyService", "message $it")

            if (it == "STOP_FROM_MESSAGE") stopSelf()
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService", "onDestroy...")
    }
}