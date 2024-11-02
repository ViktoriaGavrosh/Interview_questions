package com.viktoriagavrosh.interview.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viktoriagavrosh.interview.data.database.dao.QuestionDao
import com.viktoriagavrosh.interview.data.database.dao.TopicDao
import com.viktoriagavrosh.interview.model.QuestionDb
import com.viktoriagavrosh.interview.model.TopicDb

interface AppDatabase {
    fun questionDao(): QuestionDao
    fun topicDao(): TopicDao
}

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [QuestionDb::class, TopicDb::class],
    version = 1,
    exportSchema = false,
)
internal abstract class AppRoomDatabase : RoomDatabase(), AppDatabase {
    abstract override fun questionDao(): QuestionDao
    abstract override fun topicDao(): TopicDao
}

/**
 *  Function build [AppRoomDatabase] object
 *
 *  @param context local context
 *  @return [AppDatabase] object
 */
internal fun getDatabase(context: Context): AppDatabase {
    val appRoomDatabase = Room.databaseBuilder(
        context = context,
        klass = AppRoomDatabase::class.java,
        name = "interview"
    )
        .build()

    return appRoomDatabase
}
