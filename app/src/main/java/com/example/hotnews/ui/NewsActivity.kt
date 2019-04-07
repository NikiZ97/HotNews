package com.example.hotnews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hotnews.R

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        showNewsFragment()
    }

    private fun showNewsFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, NewsFragment.newInstance())
            .commit()
    }
}
