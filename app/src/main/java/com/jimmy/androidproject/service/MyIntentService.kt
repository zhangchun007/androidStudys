package com.jimmy.androidproject.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2024/6/26
 * @Version:        1.0
 */
class MyIntentService(var name: String) : IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        Log.i("MyIntentService", "Thread id is " + Thread.currentThread().getId());
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}