package com.example.iapps_test_task.domain

import com.example.iapps_test_task.base.BaseApiService
import com.example.iapps_test_task.base.Resource
import com.example.iapps_test_task.base.ResourceHelper
import com.example.iapps_test_task.data.local.dao.FeedDao
import com.example.iapps_test_task.data.local.entity.toFeedModel
import com.example.iapps_test_task.data.remote.ApiService
import com.example.iapps_test_task.di.IoDispatcher
import com.example.iapps_test_task.model.ItemsItem
import com.example.iapps_test_task.model.toFeedEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    helper: ResourceHelper,
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val feedDao: FeedDao,
) : IFeedRepository, BaseApiService(helper) {

    override val feedStream: Flow<List<ItemsItem>>
        get() = feedDao.getFeedStream().map { list -> list.map { it.toFeedModel() } }

    override suspend fun getFeedList(): Resource<Unit> = withContext(ioDispatcher) {
        val result = safeApiCall { apiService.getFeedList() }
        if (result is Resource.Success) {
            val localFeeds = feedDao.getFeeds()
            val titleWithId = localFeeds.associate { it.title to it.id }
            val remoteFeeds = result.data?.items ?: emptyList<ItemsItem>()
            val feeds =
                remoteFeeds.map {
                    it?.toFeedEntity()?.copy(
                        id = titleWithId[it.title] ?: 0,
                        description = it.description?.let { it1 ->
                            getFormattedDescription(
                                it1
                            )
                        } ?: "")
                }.requireNoNulls()
            feedDao.insertFeed(feeds)

            val titles = remoteFeeds.map { it?.title }
            val deletedFeeds = localFeeds.filter { !titles.contains(it.title) }
            feedDao.deleteFeed(deletedFeeds)
            Resource.Success(data = null)
        } else {
            Resource.Error(message = result.message, data = null)
        }
    }

    private fun getFormattedDescription(description: String): String {
        val document: Document = Jsoup.parse(description)

        // Remove all img tags
        val imgTags: List<Element> = document.select("img")
        imgTags.forEach { it.remove() }

        return document.body().html()
    }
}