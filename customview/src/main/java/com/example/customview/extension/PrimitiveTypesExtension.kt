package com.example.customview.extension

import android.content.Context

fun Int.getString(actionTime: String, context: Context): String {
    return "$actionTime'  ${context.getString(this)}"
}

fun String.getName(): String {
    val name = this.substringBefore(SPACE_CHAR)[FIRST_INDEX]
    val surname = this.substringAfter(SPACE_CHAR)
    return "$surname $name."
}

private const val SPACE_CHAR = " "
private const val FIRST_INDEX = 0