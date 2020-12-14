package com.apolis.homero.helpers

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT)
}

fun Context.d(message: String) {
    Log.d("Homero", message)
}