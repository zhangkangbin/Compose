package com.compose.base.delegate

import android.util.Log

/**
 * 日志跟踪委托类
 */
 class TraceDelegate :TraceDelegateImpl{

    override fun setEvent(event: String) {

        Log.d("TraceDelegate","TraceDelegate--:$event")
    }




}

