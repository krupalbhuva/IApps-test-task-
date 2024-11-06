package com.example.iapps_test_task.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.iapps_test_task.data.local.dao.FeedDao
import com.example.iapps_test_task.data.local.entity.FeedEntity

@Database(entities = [FeedEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val feedDao: FeedDao
}