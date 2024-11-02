package com.viktoriagavrosh.interview.ui.screens

sealed class ScreenState {
    data object Loading: ScreenState()
    data class Success <T> (val data: T): ScreenState()
    data class Error (val error: Throwable? = null): ScreenState()
}
