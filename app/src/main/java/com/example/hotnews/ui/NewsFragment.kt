package com.example.hotnews.ui

import com.example.hotnews.R
import com.example.hotnews.base.BaseFragment

class NewsFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = NewsFragment()
    }

    override fun getResLayoutId() = R.layout.fragment_news

}
