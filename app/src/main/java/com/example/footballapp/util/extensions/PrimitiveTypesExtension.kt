package com.example.footballapp.util.extensions

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

fun Int.getString(actionTime: String, context: Context): String {
    return actionTime + context.getString(this)
}

fun String.getName(): String {
    val name = this.substringBefore(SPACE_CHAR)[FIRST_INDEX]
    val surname = this.substringAfter(SPACE_CHAR)
    return "$surname $name."
}

fun Long.formatDate(): String {
    val sdf = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
    return sdf.format(this)
}

fun Double.getTime(): String {
    return "${this.toInt()}'"
}

fun Int.getScore(secondTeamScore: Int): String {
    return "$this : $secondTeamScore"
}

fun Int.getPossession(): String {
    return "$this %"
}

private const val SPACE_CHAR = " "
private const val FIRST_INDEX = 0
private const val DATE_PATTERN = "dd MMMM yyyy"