package com.example.footballapp.util.extensions

import android.content.Context

fun Int.getString(actionTime: String, context: Context): String {
    return actionTime + context.getString(this)
}