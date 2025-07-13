package com.cscorner.newsx.ui.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cscorner.newsx.R
import com.cscorner.newsx.adapters.NewsAdapter
import com.cscorner.newsx.databinding.FragmentFavourites2Binding
import com.cscorner.newsx.ui.NewsActivity
import com.cscorner.newsx.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar


class FavouritesFragment: Fragment(R.layout.fragment_favourites2) {
lateinit var newsViewModel: NewsViewModel
lateinit var newsAdapter: NewsAdapter
lateinit var binding: FragmentFavourites2Binding

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentFavourites2Binding.bind(view)

    newsViewModel = (activity as NewsActivity).newsViewModel
    setUpFavouritesRecycler()

    newsAdapter.setOnItemClickListener {
        val bundle = Bundle().apply {
            putSerializable("article", it)
        }
        findNavController().navigate(R.id.action_favouritesFragment2_to_articlesFragment2, bundle)
    }

    val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder

        ): Boolean {
            return true

        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val article = newsAdapter.differ.currentList[position]
            newsViewModel.deleteArticle(article)
            Snackbar.make(view, "Successfully deleted article", Snackbar.LENGTH_SHORT).apply {
                setAction("Undo") {
                    newsViewModel.addTofavourites(article)
                }
                show()

            }
        }


    }
    ItemTouchHelper(itemTouchHelperCallback).apply {
        attachToRecyclerView(binding.recyclerFavourites)
    }
    newsViewModel.getFavouriteNews().observe(viewLifecycleOwner, { article ->
        newsAdapter.differ.submitList(article)
    })
}

    private fun setUpFavouritesRecycler() {
        newsAdapter = NewsAdapter()
        binding.recyclerFavourites.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}