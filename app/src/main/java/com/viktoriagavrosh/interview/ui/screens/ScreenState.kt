package com.viktoriagavrosh.interview.ui.screens

sealed class ScreenState<T> {
    class Loading<T> : ScreenState<T>()
    data class Success<T>(val data: T) : ScreenState<T>()
    data class Error<T>(val error: Throwable? = null) : ScreenState<T>()
}
