package com.petproject.newsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}