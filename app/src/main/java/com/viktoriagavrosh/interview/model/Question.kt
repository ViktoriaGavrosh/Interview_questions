package com.viktoriagavrosh.interview.model

/**
 * Model represents a single question for ui
 *
 * @param id unique object identifier
 * @param questionText text of question
 * @param answer text of question's answer
 * @param topic name of question's topic
 */
data class Question(
    val id: Int = 0,
    val questionText: String = "",
    val answer: String = "",
    val topic: String = "",
)
