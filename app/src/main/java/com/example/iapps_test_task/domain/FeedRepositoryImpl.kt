package com.example.iapps_test_task.domain

import com.example.iapps_test_task.base.BaseApiService
import com.example.iapps_test_task.base.Resource
import com.example.iapps_test_task.base.ResourceHelper
import com.example.iapps_test_task.data.remote.ApiService
import com.example.iapps_test_task.di.IoDispatcher
import com.example.iapps_test_task.model.ItemsItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    helper: ResourceHelper,
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : IFeedRepository, BaseApiService(helper) {

    private val _feedStream = MutableStateFlow<List<ItemsItem>>(emptyList())
    override val feedStream: Flow<List<ItemsItem>> = _feedStream


    override suspend fun getFeedList(): Resource<Unit> = withContext(ioDispatcher) {
        val result = safeApiCall { apiService.getFeedList() }
        if (result is Resource.Success) {
            _feedStream.emit(result.data?.items?.map { it?.copy(description = it.description?.let { it1 ->
                getFormattedDescription(
                    it1
                )
            } ?: "") }?.requireNoNulls() ?: emptyList() )

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