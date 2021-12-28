package com.jimmy.customview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jimmy.customview.R
import com.jimmy.customview.databinding.ActivityCustomview1Binding
import com.jimmy.customview.inflate
import com.jimmy.customview.widget.*

/**
 * @Description:    canvas变换与操作
 * @Author:         zhangchun
 * @CreateDate:     2021/7/10
 * @Version:        1.0
 */
class CustomViewActivity5 : AppCompatActivity() {
    private val binding by inflate<ActivityCustomview1Binding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customview1)
        binding.root.addView(CustomView5(this))
    }
}