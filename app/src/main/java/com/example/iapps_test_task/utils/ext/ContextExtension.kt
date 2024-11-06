package com.example.iapps_test_task.utils.ext

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openLink(url: String) {
    try {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent, null)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}