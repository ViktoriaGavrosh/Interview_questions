package com.viktoriagavrosh.interview.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.viktoriagavrosh.interview.ui.navigation.InterviewNavHost

@Composable
fun InterviewApp(
    modifier: Modifier = Modifier,
) {
    InterviewNavHost(
        modifier = modifier,
    )
}
