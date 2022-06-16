package com.juarez.androidbroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    override fun onResume() {
        super.onResume()
        registerReceiver(getBatteryLevel, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        registerReceiver(getAirplaneMode, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        registerReceiver(getWifiState, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(getBatteryLevel)
        unregisterReceiver(getAirplaneMode)
        unregisterReceiver(getWifiState)
    }

    private val getBatteryLevel = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val batteryLevel = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
            batteryLevel.let { percentage ->
                Log.d("Battery", " percentage $percentage")
            }
        }
    }

    private val getAirplaneMode = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, intent: Intent?) {
            val airplaneMode = intent?.getBooleanExtra("state", false)

            airplaneMode?.let { isActive ->
                if (isActive) {
                    Toast.makeText(this@MainActivity, "airplane mode", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "normal mode", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val getWifiState = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, intent: Intent?) {
            val wifiState =
                intent?.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)

            wifiState?.let { state ->
                when (state) {
                    WifiManager.WIFI_STATE_ENABLED -> {
                        Log.d("WifiState", "ENABLED")
                    }
                    WifiManager.WIFI_STATE_DISABLED -> {
                        Log.d("WifiState", "DISABLED")
                    }
                    else -> {
                        Log.d("WifiState", "UNKNOWN")
                    }
                }
            }
        }

    }
}