package com.viktoriagavrosh.interview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model represents a single topic given from database
 *
 * @param id unique object identifier
 * @param title title of topic
 */
@Entity(tableName = "topic")
data class TopicDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
)
