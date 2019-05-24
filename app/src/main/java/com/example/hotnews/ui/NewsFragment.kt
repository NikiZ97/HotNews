package com.example.hotnews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotnews.R
import com.example.hotnews.api.response.ArticleResponse
import com.example.hotnews.base.BaseFragment
import com.example.hotnews.ui.adapters.NewsAdapter
import com.leodroidcoder.genericadapter.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment(), OnRecyclerItemClickListener {

    private val newsViewModel: NewsViewModel by viewModel()
    private lateinit var newsAdapter: NewsAdapter

    companion object {
        @JvmStatic
        fun newInstance() = NewsFragment()
    }

    override fun getResLayoutId() = R.layout.fragment_news

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        newsAdapter = NewsAdapter(context!!, this)
        return inflater.inflate(getResLayoutId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newsViewModel.news.observe(viewLifecycleOwner, Observer {
            updateNewsList(it.articles)
        })
        newsViewModel.error.observe(viewLifecycleOwner, Observer {
            showError(it)
        })
        newsViewModel.progressState.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
    }

    override fun onItemClick(position: Int) {
        // TODO: need to open NewsDetailActivity and show article details
    }

    private fun showError(errorMessage: String) {
        error.visibility = View.VISIBLE
        error.text = errorMessage
    }

    private fun updateNewsList(articles: List<ArticleResponse>) {
        newsRecyclerView.layoutManager = LinearLayoutManager(context)
        newsRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        newsAdapter.updateItems(articles)
        newsRecyclerView.adapter = newsAdapter
    }
}
