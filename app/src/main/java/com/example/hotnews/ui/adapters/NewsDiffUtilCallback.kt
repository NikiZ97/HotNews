package com.example.hotnews.ui.adapters

import com.example.hotnews.api.response.ArticleResponse
import com.leodroidcoder.genericadapter.BaseDiffCallback

class NewsDiffUtilCallback(private val oldArticles: List<ArticleResponse>, private val newArticles: List<ArticleResponse>)
    : BaseDiffCallback<ArticleResponse>(oldArticles, newArticles) {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldArticles[oldItemPosition].title == newArticles[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldArticles[oldItemPosition].title == newArticles[newItemPosition].title
    }
}