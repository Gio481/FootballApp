package com.example.footballapp.util.extensions

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

fun View.isVisible(visibility: Boolean) {
    this.isVisible = visibility
}

fun View.setBackground(context: Context, item:Int){
    this.background = ContextCompat.getDrawable(context, item)
}

fun View.setBackgroundTint(context: Context, item: Int){
    this.background.setTint(ContextCompat.getColor(context, item))
}