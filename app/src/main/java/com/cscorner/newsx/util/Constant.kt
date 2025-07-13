package com.cscorner.newsx.util

class Constant {
    companion object{  // Anything which we will write could be fetched from anywhere in class.
        const val API_key="868ffc90dc80419785e3ff608a047f56"
        const val Base_Url="https://newsapi.org/"
        const val SEARCH_NEWS_TIME_DELAY=500L  // means within 500ms the search process will get completed.
        const val QUERY_PAGE_SIZE=20  // In one time 20 articles will get fetched on a single page.
    }
}