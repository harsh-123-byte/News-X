package com.cscorner.newsx.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.cscorner.newsx.R
import com.cscorner.newsx.databinding.FragmentArticlesBinding
import com.cscorner.newsx.ui.NewsActivity
import com.cscorner.newsx.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar


class ArticlesFragment : Fragment(R.layout.fragment_articles) {
    lateinit var newsViewModel: NewsViewModel
    val args: ArticlesFragmentArgs by navArgs()
    lateinit var binding: FragmentArticlesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentArticlesBinding.bind(view)

        newsViewModel=(activity as NewsActivity).newsViewModel
        val article=args.article

        // Applying WebView.
        binding.webView.apply {
            webViewClient= WebViewClient()
            loadUrl(article.url)
        }
        binding.fab.setOnClickListener{

            newsViewModel.addTofavourites(article)
            Snackbar.make(view,"Article saved successfully",Snackbar.LENGTH_SHORT).show()

        }
    }

}