package com.compose.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * 基类
 */
@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}