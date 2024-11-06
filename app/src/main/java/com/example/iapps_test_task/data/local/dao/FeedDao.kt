package com.example.iapps_test_task.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.iapps_test_task.data.local.entity.FeedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeed(feeds: List<FeedEntity>)

    @Delete
    suspend fun deleteFeed(feeds: List<FeedEntity>)

    @Query("SELECT * FROM feedentity ORDER BY published DESC")
    fun getFeedStream(): Flow<List<FeedEntity>>

    @Query("SELECT * FROM feedentity ORDER BY published DESC")
    suspend fun getFeeds(): List<FeedEntity>
}