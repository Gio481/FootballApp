package com.example.customview.util

import android.view.View
import com.example.customview.extension.isVisible

inline fun <T> getActionType(view: View, tryAction: () -> T): T? {
    return try {
        tryAction.invoke()
    } catch (e: Exception) {
        view.isVisible(false)
        null
    }
}