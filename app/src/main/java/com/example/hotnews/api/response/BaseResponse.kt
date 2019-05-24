package com.example.hotnews.api.response

import com.google.gson.annotations.SerializedName

class BaseResponse(
    @SerializedName("status")
    var status: String,
    @SerializedName("totalResults")
    var totalResults: Int,
    @SerializedName("articles")
    var articles: List<ArticleResponse>
)