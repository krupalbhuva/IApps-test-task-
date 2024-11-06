package com.example.iapps_test_task.data.remote

import com.example.iapps_test_task.model.FeedListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/services/feeds/photos_public.gne")
    suspend fun getFeedList(
        @Query("format") format: String = "json",
        @Query("tags") tag: String = "cat",
        @Query("nojsoncallback") nojsoncallback: Int = 1,
    ): Response<FeedListResponse?>
}