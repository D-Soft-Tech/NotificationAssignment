package com.example.assignment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat

const val CHANNEL_ID = "1234"

class MainActivity : AppCompatActivity() {
    //view holder variables
    private lateinit var notify: Button
    private lateinit var move: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create an explicit intent for an Activity in your app
        val intent = Intent(this, SecondActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        notify = findViewById(R.id.btn_notify)
        move = findViewById(R.id.btn_move)

        move.setOnClickListener { view ->
            nextActivity()
        }

        notify.setOnClickListener { view ->
            createNotificationChannel()
        }
    }

    private fun nextActivity()
    {
       val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("data", "Active")
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.hearts)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_content))
                .setAutoCancel(true)

            builder.setContentIntent(pendingIntent)
            notificationManager.notify(0, builder.build())
        }
    }
}