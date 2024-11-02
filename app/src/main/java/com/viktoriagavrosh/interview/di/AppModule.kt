package com.viktoriagavrosh.interview.di

import android.content.Context
import com.viktoriagavrosh.interview.data.AppRepository
import com.viktoriagavrosh.interview.data.DatabaseRepository
import com.viktoriagavrosh.interview.data.database.AppDatabase
import com.viktoriagavrosh.interview.data.database.getDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideRepository(appDatabase: AppDatabase): AppRepository {
        return DatabaseRepository(appDatabase)
    }
}
