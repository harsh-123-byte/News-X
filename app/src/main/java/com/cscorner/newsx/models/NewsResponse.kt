package com.cscorner.newsx.models

data class NewsResponse(
    val articles: MutableList<Article>,   // mutable list--> So that the data can be changed.
    val status: String,
    val totalResults: Int
)