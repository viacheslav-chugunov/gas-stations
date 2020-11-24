package com.dev.gasstations.app.ext

import android.content.Context
import android.content.res.Resources
import android.graphics.PorterDuff
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.dev.gasstations.R

fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    val toast = Toast.makeText(this, message, length)
    toast.view.run {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            background.setColorFilter(getColor(R.color.red_500), PorterDuff.Mode.SRC_IN)
            findViewById<TextView>(android.R.id.message).setTextColor(getColor(R.color.white))
        }
    }
    toast.show()
}

fun Context.showToast(message: Int, length: Int = Toast.LENGTH_SHORT) {
    showToast(getString(message), length)
}