package com.jimmy.androidproject.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2024/6/26
 * @Version:        1.0
 */
class MyBindService : Service() {

    private var mBinder = DownloadBinder();

    inner class DownloadBinder : Binder() {
        public fun startDownload() {
            Log.e("MyBindService", "startDownload() executed");
        }

        public fun getPrograss(): Int {
            Log.e("MyBindService", "getPrograss() executed");
            return 0;
        }

    }


    override fun onBind(intent: Intent?): IBinder? {
        Log.e("MyBindService", "onBind");
       return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("MyBindService", "onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("MyBindService", "onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("MyBindService", "onUnbind()")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.e("MyBindService", "onDestroy()")
        super.onDestroy()
    }
}