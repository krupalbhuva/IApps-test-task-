package com.example.iapps_test_task.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.iapps_test_task.model.ItemsItem
import com.example.iapps_test_task.model.Media

@Entity
data class FeedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "link")
    val link: String,

    @ColumnInfo(name = "media")
    val media: String,

    @ColumnInfo(name = "date_taken")
    val dateTaken: String?,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "published")
    val published: String?,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "author_id")
    val authorId: String,

    @ColumnInfo(name = "tags")
    val tags: String,
)

fun FeedEntity.toFeedModel() = ItemsItem(
    title = title,
    link = link,
    media = Media(media),
    dateTaken = dateTaken,
    description = description,
    published = published,
    author = author,
    authorId = authorId,
    tags = tags
)


