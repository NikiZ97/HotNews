package com.example.hotnews.network

import android.content.Context
import android.net.ConnectivityManager

class ConnectionManagerImp(private val context: Context): ConnectionManager  {

    override fun isNetworkNotAvailable(): Boolean {
        val conMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val netInfo = conMgr?.activeNetworkInfo
        return netInfo == null
    }

}