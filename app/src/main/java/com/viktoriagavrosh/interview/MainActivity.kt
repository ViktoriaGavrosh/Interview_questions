package com.viktoriagavrosh.interview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.viktoriagavrosh.interview.ui.InterviewApp
import com.viktoriagavrosh.interview.ui.theme.InterviewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        (application as InterviewApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewTheme {
                InterviewApp()
            }
        }
    }
}
