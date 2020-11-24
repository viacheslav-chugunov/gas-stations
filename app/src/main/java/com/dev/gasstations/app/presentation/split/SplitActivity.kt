package com.dev.gasstations.app.presentation.split

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev.gasstations.app.ext.startActivityInNewTask
import com.dev.gasstations.app.presentation.main.MainActivity
import com.dev.gasstations.data.firebase.GasStationFirebase
import com.dev.gasstations.data.service.SynchronizationService
import com.dev.gasstations.domain.entity.GasStation

class SplitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startSynchronization()
        runApp()
    }

    private fun startSynchronization() {
        val intent = Intent(this, SynchronizationService::class.java)
        startService(intent)
    }

    private fun runApp() {
        val intent = Intent(this, MainActivity::class.java)
        startActivityInNewTask(intent)
    }

}