package com.juarez.androidbroadcastreceiver

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.juarez.androidbroadcastreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartService.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
            Toast.makeText(this, "service started...", Toast.LENGTH_SHORT).show()
        }

        binding.btnStopService.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
            Toast.makeText(this, "service stopped...", Toast.LENGTH_SHORT).show()
        }

        binding.btnSendMessage.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            intent.putExtra("EXTRA_MESSAGE", "hola")
            startService(intent)
            Toast.makeText(this, "service started...", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService", "Main Activity onDestroy")
    }
}