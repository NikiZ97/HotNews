package com.example.hotnews.api.response

import com.google.gson.annotations.SerializedName


class ArticleResponse(
    @SerializedName("source")
    var source: SourceResponse?,
    @SerializedName("author")
    var author: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("urlToImage")
    var urlToImage: String,
    @SerializedName("publishedAt")
    var publishedAt: String
)