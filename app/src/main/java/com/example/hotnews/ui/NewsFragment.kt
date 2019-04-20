package com.example.hotnews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotnews.R
import com.example.hotnews.api.response.ArticleResponse
import com.example.hotnews.base.BaseFragment
import com.example.hotnews.ui.adapters.NewsAdapter
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment() {

    private val newsViewModel: NewsViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance() = NewsFragment()
    }

    override fun getResLayoutId() = R.layout.fragment_news

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getResLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.getBreakingNews()
        newsViewModel.news.observe(this, Observer {
            updateNewsList(it.articles)
        })
    }

    private fun updateNewsList(articles: List<ArticleResponse>?) {
        newsRecyclerView.layoutManager = LinearLayoutManager(context)
        newsRecyclerView.adapter = NewsAdapter(articles ?: listOf(), context!!) {}
    }

}
