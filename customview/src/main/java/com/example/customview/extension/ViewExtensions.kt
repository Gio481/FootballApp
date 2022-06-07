package com.example.customview.extension

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide

fun View.isVisible(visibility: Boolean) {
    this.isVisible = visibility
}

fun View.setBackground(context: Context, item:Int){
    this.background = ContextCompat.getDrawable(context, item)
}

fun View.setBackgroundTint(context: Context, item: Int){
    this.background.setTint(ContextCompat.getColor(context, item))
}

fun TextView.setColor(color:Int){
    this.setTextColor(ContextCompat.getColor(this.context, color))
}

fun ImageView.setImage(url:String){
    Glide.with(this).load(url).into(this)
}

