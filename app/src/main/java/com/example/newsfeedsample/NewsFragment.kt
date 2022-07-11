package com.example.newsfeedsample

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfeedsample.adapter.NewsAdapter
import kotlinx.android.synthetic.main.fragment_news.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewsFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        setupView(view)
        category.observe(viewLifecycleOwner) { categoryName ->
            updateAdapter(categoryName)
        }
        setHasOptionsMenu(true)

        return view
    }

    companion object {
        val category = MutableLiveData("music")
    }

    private fun setupView(view: View) {
        adapter = NewsAdapter(requireContext(), mutableMapOf("category" to category.value))
        view.rc_view.adapter = adapter
        view.rc_view.layoutManager = LinearLayoutManager(requireContext())
        newsViewModel = NewsViewModel()
    }

    private fun updateAdapter(categoryName: String) {
        lifecycleScope.launch {
            newsViewModel.getNews(categoryName).collectLatest {
                adapter.params["category"] = categoryName
                adapter.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val search = menu.findItem(R.id.search_menu)
        searchView = search?.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchView.clearFocus()
        if (query != null) {
            category.value = query
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return true
    }
}