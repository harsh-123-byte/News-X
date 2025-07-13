package com.cscorner.newsx.repository

import com.cscorner.newsx.api.RetrofitInstance
import com.cscorner.newsx.db.ArticleDao
import com.cscorner.newsx.db.ArticleDatabase
import com.cscorner.newsx.models.Article

class NewsRepository(val db:ArticleDatabase) {

    // Basically what this function does is help us to select or find the articles based on the country code and page number.
    suspend fun getHeadlines(countryCode:String,pageNumber:Int)=
        RetrofitInstance.api.getHeadlines(countryCode,pageNumber)  // retrofit instance me isliye ja rhe hain kyuki hamara api usi me called hai.

    suspend fun searchForNews(searchQuery:String,pageNumber:Int)=
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)

    suspend fun upsert(article: Article)=db.getArticleDao().upsert(article)

    fun getFavouriteNews()=db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article)=db.getArticleDao().deleteArticle(article)


}