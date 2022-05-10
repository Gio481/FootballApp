package com.example.footballapp.util.extensions

fun String?.possessionProgress(): Int {
    var progress = "0"
    this?.forEach {
        if (it in 48.toChar()..56.toChar()) {
            progress += it
        }
    }
    return progress.toInt()
}