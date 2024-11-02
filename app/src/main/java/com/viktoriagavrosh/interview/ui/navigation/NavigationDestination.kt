package com.viktoriagavrosh.interview.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object TopicMenu

@Serializable
data class QuestionMenu(
    val topicId: Int = 0,
)

@Serializable
data class Game(
    val topicId: Int = 0,
)

@Serializable
data class QuestionDetail(
    val questionId: Int = 0,
)

@Serializable
data class EditQuestion(
    val questionId: Int = 0,
)

@Serializable
data class EditTopic(
    val topicId: Int = 0,
)
