package com.example.hotnews.api.response

import com.google.gson.annotations.SerializedName

class BaseResponse {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("totalResults")
    var totalResults: Int? = null

    @SerializedName("articles")
    var articles: List<ArticleResponse>? = null
}