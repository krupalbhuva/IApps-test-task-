package com.example.iapps_test_task.di

import com.example.iapps_test_task.domain.FeedRepositoryImpl
import com.example.iapps_test_task.domain.IFeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindFeedRepository(feedRepositoryImpl: FeedRepositoryImpl): IFeedRepository
}