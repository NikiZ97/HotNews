package com.example.hotnews.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotnews.R
import com.example.hotnews.api.response.ArticleResponse
import kotlinx.android.synthetic.main.layout_news_item.view.*

class NewsAdapter(private val articles: List<ArticleResponse>, private val context: Context,
                  private val clickListener: (ArticleResponse) -> Unit): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_news_item, parent, false))
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        holder.bind(articles[position], clickListener)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(article: ArticleResponse, clickListener: (ArticleResponse) -> Unit) {
            itemView.articleName.text = article.title
            itemView.setOnClickListener { clickListener(article) }
        }
    }


}