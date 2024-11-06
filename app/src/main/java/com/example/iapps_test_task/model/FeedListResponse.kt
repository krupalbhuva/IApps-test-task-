package com.example.iapps_test_task.model

import com.google.gson.annotations.SerializedName

data class FeedListResponse(

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("modified")
    val modified: String? = null,

    @field:SerializedName("generator")
    val generator: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("items")
    val items: List<ItemsItem?>? = null
)

data class Media(

    @field:SerializedName("m")
    val m: String? = null
)

data class ItemsItem(

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("media")
    val media: Media? = null,

    @field:SerializedName("published")
    val published: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("author_id")
    val authorId: String? = null,

    @field:SerializedName("date_taken")
    val dateTaken: String? = null,

    @field:SerializedName("tags")
    val tags: String? = null
)
