package com.cscorner.newsx.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.cscorner.newsx.repository.NewsRepository

class NewsViewModelProviderFactory(val app: Application, val newsRepository: NewsRepository):ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app,newsRepository) as T
    }

}