package com.example.bootcounter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.bootcounter.work.NotificationHelper
import com.example.bootcounter.work.NotifyWork
import com.example.myapplication.R
import kotlinx.coroutines.flow.collectLatest
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("123TAG", "MainActivity onCreate: notifi ${NotificationHelper.isNotificationVisible(this)}")

        lifecycleScope.launchWhenCreated {
            viewModel.stateFlow.collectLatest { records ->
                val textView = findViewById<TextView>(R.id.tv)
                if (records.isNotEmpty())  {
                    val sb = StringBuilder()
                    records.forEach {
                        sb.append(it)
                        sb.append("\n")
                    }
                    textView.text = sb.toString()
                }
            }
        }
    }
}