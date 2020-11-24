package com.dev.gasstations.app.ext

import android.content.Intent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dev.gasstations.R

fun AppCompatActivity.bindToolbar(title: String = getString(R.string.app_name), enableUpButton: Boolean = false) {
    val toolbar: Toolbar = findViewById(R.id.toolbar)
    setSupportActionBar(toolbar)

    val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
    toolbarTitle.text = title

    supportActionBar?.setDisplayShowTitleEnabled(false)
    supportActionBar?.setDisplayHomeAsUpEnabled(enableUpButton)
}

fun AppCompatActivity.bindToolbar(title: Int, enableUpButton: Boolean = false) =
        bindToolbar(getString(title), enableUpButton)

fun AppCompatActivity.startActivityInNewTask(intent: Intent) {
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
    finish()
}