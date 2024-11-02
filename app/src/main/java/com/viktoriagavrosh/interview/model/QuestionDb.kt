package com.viktoriagavrosh.interview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model represents a single question given from database
 *
 * @param id unique object identifier
 * @param questionText text of question
 * @param answer text of question's answer
 * @param topic name of question's topic
 */
@Entity(tableName = "question")
data class QuestionDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "text") val questionText: String,
    @ColumnInfo(name = "answer") val answer: String,
    @ColumnInfo(name = "topic") val topic: String,
)
