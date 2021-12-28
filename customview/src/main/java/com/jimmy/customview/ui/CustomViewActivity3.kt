package com.jimmy.customview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jimmy.customview.R
import com.jimmy.customview.databinding.ActivityCustomview1Binding
import com.jimmy.customview.inflate
import com.jimmy.customview.widget.CustomView1
import com.jimmy.customview.widget.CustomView2
import com.jimmy.customview.widget.CustomView3

/**
 * @Description:    区域（range）
 * @Author:         zhangchun
 * @CreateDate:     2021/7/10
 * @Version:        1.0
 */
class CustomViewActivity3 : AppCompatActivity() {
    private val binding by inflate<ActivityCustomview1Binding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customview1)
        binding.root.addView(CustomView3(this))
    }
}