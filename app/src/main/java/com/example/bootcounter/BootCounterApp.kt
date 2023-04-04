package com.example.bootcounter

import android.app.Application
import com.example.bootcounter.work.NotifyWork

class BootCounterApp: Application() {

    override fun onCreate() {
        super.onCreate()

        NotifyWork.enqueueWork(this)
    }
}