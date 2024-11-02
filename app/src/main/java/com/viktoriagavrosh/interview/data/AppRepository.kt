package com.viktoriagavrosh.interview.data

import com.viktoriagavrosh.interview.data.database.AppDatabase
import com.viktoriagavrosh.interview.model.Question
import com.viktoriagavrosh.interview.model.Topic
import com.viktoriagavrosh.interview.model.toQuestion
import com.viktoriagavrosh.interview.model.toQuestionDb
import com.viktoriagavrosh.interview.model.toTopic
import com.viktoriagavrosh.interview.model.toTopicDb
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * provide data for ui from data source
 */
interface AppRepository {
    /**
     * Retrieve all questions from given data source by topic
     *
     * @param topicName topic name of item
     * @return flow of [RequestResult] of [Question]
     */
    fun getQuestionsByTopic(topicName: String): Flow<RequestResult<List<Question>>>

    /**
     * Retrieve all topics from given data source
     *
     * @return flow of [RequestResult] of [Topic]
     */
    fun getAllTopics(): Flow<RequestResult<List<Topic>>>

    /**
     * Retrieve one question from given data source by id
     *
     * @param id unique id of item
     * @return flow of [Question]
     */
    fun getQuestionById(id: Int): Flow<RequestResult<Question>>

    /**
     * Retrieve one topic from given data source by topic
     *
     * @return flow of [RequestResult] of [Topic]
     */
    fun getTopicById(id: Int): Flow<RequestResult<Topic>>

    /**
     * Update question into data source
     *
     * @param question new question
     */
    suspend fun updateQuestion(question: Question)

    /**
     * Update topic into data source
     *
     * @param topic new topic
     */
    suspend fun updateTopic(topic: Topic)

    /**
     * Insert question into data source
     *
     * @param question new question
     */
    suspend fun insertQuestion(question: Question)

    /**
     * Insert topic into data source
     *
     * @param topic new topic
     */
    suspend fun insertTopic(topic: Topic)

    /**
     * Delete question into data source
     *
     * @param question question will be deleted
     */
    suspend fun deleteQuestion(question: Question)

    /**
     * Delete topic into data source
     *
     * @param topic topic  will be deleted
     */
    suspend fun deleteTopic(topic: Topic)
}

/**
 * provide data for ui from local database
 *
 * @param appDatabase instance of [AppDatabase]
 */
class DatabaseRepository @Inject constructor(
    private val appDatabase: AppDatabase
) : AppRepository {
    override suspend fun deleteQuestion(question: Question) {
        appDatabase.questionDao().delete(question.toQuestionDb())
    }

    override fun getQuestionsByTopic(topicName: String): Flow<RequestResult<List<Question>>> {
        return try {
            appDatabase.questionDao().getAllQuestionsByTopic(topicName)
                .map {
                    RequestResult.Success(data = it.map { questionDb -> questionDb.toQuestion() })
                }
        } catch (e: Exception) {
            flow { emit(RequestResult.Error(e)) }
        }
    }

    override fun getAllTopics(): Flow<RequestResult<List<Topic>>> {
        return try {
            appDatabase.topicDao().getAllTopics()
                .map {
                    RequestResult.Success(data = it.map { topicDb -> topicDb.toTopic() })
                }
        } catch (e: Exception) {
            flow { emit(RequestResult.Error(e)) }
        }
    }

    override fun getQuestionById(id: Int): Flow<RequestResult<Question>> {
        return try {
            appDatabase.questionDao().getQuestionById(id)
                .map {
                    RequestResult.Success(data = it.toQuestion())
                }
        } catch (e: Exception) {
            flow { emit(RequestResult.Error(e)) }
        }
    }

    override fun getTopicById(id: Int): Flow<RequestResult<Topic>> {
        return try {
            appDatabase.topicDao().getTopicById(id)
                .map {
                    RequestResult.Success(data = it.toTopic())
                }
        } catch (e: Exception) {
            flow { emit(RequestResult.Error(e)) }
        }
    }

    override suspend fun updateQuestion(question: Question) {
        appDatabase.questionDao().update(question.toQuestionDb())
    }

    override suspend fun updateTopic(topic: Topic) {
        appDatabase.topicDao().update(topic.toTopicDb())
    }

    override suspend fun insertQuestion(question: Question) {
        appDatabase.questionDao().insert(question.toQuestionDb())
    }

    override suspend fun insertTopic(topic: Topic) {
        appDatabase.topicDao().insert(topic.toTopicDb())
    }

    override suspend fun deleteTopic(topic: Topic) {
        appDatabase.topicDao().delete(topic.toTopicDb())
    }
}
