package com.example.footballapp.util.extensions

import android.view.View
import androidx.core.view.isVisible

fun View.isVisible(visibility: Boolean) {
    this.isVisible = visibility
}