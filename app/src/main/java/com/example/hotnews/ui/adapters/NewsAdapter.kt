package com.example.hotnews.ui.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.example.hotnews.R
import com.example.hotnews.api.response.ArticleResponse
import com.leodroidcoder.genericadapter.BaseViewHolder
import com.leodroidcoder.genericadapter.GenericRecyclerViewAdapter
import com.leodroidcoder.genericadapter.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.layout_news_item.view.*

class NewsAdapter(context: Context, private val listener: OnRecyclerItemClickListener)
    : GenericRecyclerViewAdapter<ArticleResponse, OnRecyclerItemClickListener, NewsAdapter.NewsViewHolder>(context, listener) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(inflate(R.layout.layout_news_item, parent), listener)
    }

    class NewsViewHolder(itemView: View, listener: OnRecyclerItemClickListener):
        BaseViewHolder<ArticleResponse, OnRecyclerItemClickListener>(itemView, listener) {

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        override fun onBind(article: ArticleResponse?) {
            itemView.articleName.text = article?.title
        }
    }
}