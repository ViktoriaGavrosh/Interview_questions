package com.viktoriagavrosh.interview.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.viktoriagavrosh.interview.model.TopicDb
import kotlinx.coroutines.flow.Flow

/**
 * Interface for working with Room. It works with topic table from DB
 */
@Dao
interface TopicDao {
    /**
     * Return all rows from table
     *
     * @return flow of list [TopicDb]
     */
    @Query("SELECT * FROM topic")
    fun getAllTopics(): Flow<List<TopicDb>>

    /**
     * Return one row from table by id
     *
     * @param id unique id of topic
     * @return flow of [TopicDb]
     */
    @Query("SELECT * FROM topic WHERE id = :id")
    fun getTopicById(id: Int): Flow<TopicDb>

    /**
     * insert element into the database (topic table)
     *
     * @param topicDb object [TopicDb] that will be insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(topicDb: TopicDb)

    /**
     * update element into the database (question table)
     *
     * @param topicDb object [TopicDb] that will be updated
     */
    @Update
    suspend fun update(topicDb: TopicDb)

    /**
     * delete element into the database (question table)
     *
     * @param topicDb object [TopicDb] that will be deleted
     */
    @Delete
    suspend fun delete(topicDb: TopicDb)
}
