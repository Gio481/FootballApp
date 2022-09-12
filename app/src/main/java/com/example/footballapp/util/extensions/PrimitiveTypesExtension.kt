package com.example.footballapp.util.extensions

import java.text.SimpleDateFormat
import java.util.*

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

private const val DATE_PATTERN = "dd MMMM yyyy"