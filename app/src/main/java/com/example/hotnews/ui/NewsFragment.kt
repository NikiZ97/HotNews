package com.example.hotnews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotnews.R
import com.example.hotnews.api.response.ArticleResponse
import com.example.hotnews.base.BaseFragment
import com.example.hotnews.ui.adapters.GenericAdapter
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.layout_news_item.view.*
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newsViewModel.news.observe(viewLifecycleOwner, Observer {
            updateNewsList(it.articles)
        })
        newsViewModel.error.observe(viewLifecycleOwner, Observer {
            showInternetConnectionError(it)
        })
    }

    private fun showInternetConnectionError(stringId: Int) {
        if (stringId == R.string.no_internet_connection) {
            internetConnectionError.visibility = View.VISIBLE
        }
    }

    private fun updateNewsList(articles: List<ArticleResponse>?) {
        newsRecyclerView.layoutManager = LinearLayoutManager(context)
        newsRecyclerView.adapter = object : GenericAdapter<ArticleResponse>(articles ?: listOf()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return NewsViewHolder(view)
            }

            override fun getLayoutId(position: Int, obj: ArticleResponse) = R.layout.layout_news_item
        }
        newsRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
    }

    private class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericAdapter.Binder<ArticleResponse> {

        override fun bind(data: ArticleResponse) {
            itemView.articleName.text = data.title
        }
    }

}
