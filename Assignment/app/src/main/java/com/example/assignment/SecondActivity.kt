package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    private lateinit var status : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        status = findViewById(R.id.tv_status)
        updateStatus()
    }

    private fun updateStatus() {
        val active = intent.getStringExtra("data")
        if (active != null) {
            status.text = active
        }
    }
}