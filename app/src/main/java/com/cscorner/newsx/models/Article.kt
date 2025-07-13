package com.cscorner.newsx.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,  // we have to convert this source datatype to string so that it can be catchable by the db.
    val title: String,
    val url: String,
    val urlToImage: String
): Serializable