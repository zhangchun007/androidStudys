package com.jimmy.androidproject.service

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.jimmy.androidproject.R
import com.jimmy.androidproject.databinding.ActivityServiceBinding
import com.jimmy.androidproject.inflate


/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2024/6/26
 * @Version:        1.0
 */
class ServiceActivity : AppCompatActivity() {
    private val binding by inflate<ActivityServiceBinding>()
    private var downloadBinder: MyBindService.DownloadBinder? = null
    private var connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downloadBinder = service as MyBindService.DownloadBinder
            downloadBinder?.startDownload()
            downloadBinder?.getPrograss()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)


        val startIntent = Intent(this, MyService::class.java)
        binding.btnStartService.setOnClickListener {
            startService(startIntent)
        }
        binding.btnStopService.setOnClickListener {
            stopService(startIntent)
        }


        val startIntent2 = Intent(this, MyBindService::class.java)
        binding.btnStartBindService.setOnClickListener {
            bindService(startIntent2, connection, BIND_AUTO_CREATE)
        }

        binding.btnStopBindService.setOnClickListener {
            unbindService(connection);
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}