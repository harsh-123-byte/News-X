package com.cscorner.newsx.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cscorner.newsx.models.Article
import com.cscorner.newsx.models.NewsResponse
import com.cscorner.newsx.repository.NewsRepository
import com.cscorner.newsx.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException


class NewsViewModel(app:Application,val newsRepository: NewsRepository): AndroidViewModel(app) {

    val headLines: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var headLinesPage = 1
    var headLinesResponse: NewsResponse? = null

    // Now make similar thing for Searchnews also.

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: NewsResponse? = null
    var newSearchQuery:String? = null
    var oldSearchQuery:String? = null

    init {
        getHeadlines("us")
    }

    fun getHeadlines(countryCode:String)=viewModelScope.launch {
        headLinesInternet(countryCode)
    }

    fun searchNews(searchQuery:String)=viewModelScope.launch {
        searchNewsInternet(searchQuery)
    }


    private fun handleHeadlineResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                headLinesPage++
                if(headLinesResponse==null)
                {
                    headLinesResponse = resultResponse
                }
                else{
                    val oldArticles = headLinesResponse?.articles
                    val newArticles = resultResponse.articles
                }
                return Resource.Success(headLinesResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(searchNewsResponse==null || newSearchQuery != oldSearchQuery)
                {
                    searchNewsPage = 1
                    oldSearchQuery = newSearchQuery
                    searchNewsResponse = resultResponse
                }
                else{
                    searchNewsPage++
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: resultResponse)
            }

                }
        return Resource.Error(response.message())
    }

    fun addTofavourites(article: Article)=viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getFavouriteNews()=newsRepository.getFavouriteNews()

    fun deleteArticle(article: Article)=viewModelScope.launch {
        newsRepository.deleteArticle(article)

    }

    // Now we will create a function for checking Internet connection,if the internet connection is not present then it will return false.

    fun internetConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }

    private suspend fun headLinesInternet(countryCode:String)
    {
        headLines.postValue(Resource.Loading())
        try {
            if(internetConnection(getApplication()))
            {
                val response = newsRepository.getHeadlines(countryCode,headLinesPage)
                headLines.postValue(handleHeadlineResponse(response))
            }
            else{
                headLines.postValue(Resource.Error("No Internet Connection"))
            }
        }catch (t:Throwable){
            when(t){
                is IOException -> headLines.postValue(Resource.Error("Network Failure"))
                else-> headLines.postValue(Resource.Error("No Signal"))
                }
            }
        }

    private suspend fun searchNewsInternet(searchQuery:String)
    {
        newSearchQuery=searchQuery
        searchNews.postValue(Resource.Loading())

        try {
            if (internetConnection(this.getApplication())){
                val response = newsRepository.searchForNews(searchQuery,searchNewsPage)
                searchNews.postValue(handleSearchNewsResponse(response))
            }
            else{
                searchNews.postValue(Resource.Error("No Internet Connection"))
            }
        }catch (t:Throwable){
            when(t){
                is IOException -> searchNews.postValue(Resource.Error("Network Failure"))
                else-> searchNews.postValue(Resource.Error("No Signal"))
            }
        }

    }

    }


