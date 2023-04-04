package com.example.bootcounter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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