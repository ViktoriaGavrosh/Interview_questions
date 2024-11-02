package com.viktoriagavrosh.interview.model

fun Question.toQuestionDb(): QuestionDb {
    return QuestionDb(
        id = id,
        questionText = questionText,
        answer = answer,
        topic = topic,
    )
}

fun QuestionDb.toQuestion(): Question {
    return Question(
        id = id,
        questionText = questionText,
        answer = answer,
        topic = topic,
    )
}

fun Topic.toTopicDb(): TopicDb {
    return TopicDb(
        id = id,
        title = title,
    )
}

fun TopicDb.toTopic(): Topic {
    return Topic(
        id = id,
        title = title,
    )
}

fun Topic.toMenuItem(): MenuItem {
    return MenuItem(
        id = id,
        text = title,
        isTopic = true,
    )
}

fun Question.toMenuItem(): MenuItem {
    return MenuItem(
        id = id,
        text = questionText,
        isTopic = false,
    )
}


