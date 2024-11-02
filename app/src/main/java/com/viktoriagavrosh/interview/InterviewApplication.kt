package com.viktoriagavrosh.interview

import android.app.Application
import com.viktoriagavrosh.interview.di.AppComponent
import com.viktoriagavrosh.interview.di.DaggerAppComponent

class InterviewApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}
