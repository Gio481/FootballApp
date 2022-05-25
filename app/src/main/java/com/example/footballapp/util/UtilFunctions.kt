package com.example.footballapp.util

import android.view.View
import com.example.footballapp.util.extensions.isVisible

inline fun <T> getActionType(view: View, tryAction: () -> T): T? {
    return try {
        tryAction.invoke()
    } catch (e: Exception) {
        view.isVisible(false)
        null
    }
}