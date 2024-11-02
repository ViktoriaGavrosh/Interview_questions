package com.viktoriagavrosh.interview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model represents a single topic for ui
 *
 * @param id unique object identifier
 * @param title title of topic
 */
data class Topic(
    val id: Int = 0,
    val title: String = "",
)
