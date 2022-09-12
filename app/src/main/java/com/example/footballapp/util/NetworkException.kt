package com.example.footballapp.util

import okio.IOException

class NetworkException : IOException() {
    override val message: String = ERROR_MESSAGE

    companion object {
        private const val ERROR_MESSAGE = "no internet connection"
    }
}