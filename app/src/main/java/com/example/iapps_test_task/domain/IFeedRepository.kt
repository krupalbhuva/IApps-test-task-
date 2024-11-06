package com.example.iapps_test_task.domain

import com.example.iapps_test_task.base.Resource
import com.example.iapps_test_task.model.ItemsItem
import kotlinx.coroutines.flow.Flow

interface IFeedRepository {
    val feedStream: Flow<List<ItemsItem>>
    suspend fun getFeedList(): Resource<Unit>
}