package com.viktoriagavrosh.interview.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.viktoriagavrosh.interview.model.QuestionDb
import kotlinx.coroutines.flow.Flow

/**
 * Interface for working with Room. It works with questions table from DB
 */
@Dao
interface QuestionDao {
    /**
     * Return all rows from table by topic
     *
     * @param topic name of question's topic
     * @return flow of list [QuestionDb]
     */
    @Query("SELECT * FROM question WHERE topic = :topic")
    fun getAllQuestionsByTopic(topic: String): Flow<List<QuestionDb>>

    /**
     * Return one row from table by id
     *
     * @param id unique id of question
     * @return flow of [QuestionDb]
     */
    @Query("SELECT * FROM question WHERE id = :id")
    fun getQuestionById(id: Int): Flow<QuestionDb>

    /**
     * insert element into the database (question table)
     *
     * @param questionDb object [QuestionDb] that will be insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(questionDb: QuestionDb)

    /**
     * update element into the database (question table)
     *
     * @param questionDb object [QuestionDb] that will be updated
     */
    @Update
    suspend fun update(questionDb: QuestionDb)

    /**
     * delete element into the database (question table)
     *
     * @param questionDb object [QuestionDb] that will be deleted
     */
    @Delete
    suspend fun delete(questionDb: QuestionDb)
}
