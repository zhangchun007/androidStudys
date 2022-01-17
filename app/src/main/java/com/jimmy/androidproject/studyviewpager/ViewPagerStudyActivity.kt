package com.jimmy.androidproject.studyviewpager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jimmy.androidproject.R
import com.jimmy.androidproject.databinding.ActivityViewpagerStudyBinding
import com.jimmy.androidproject.inflate

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/12/25
 * @Version:        1.0
 */
class ViewPagerStudyActivity : AppCompatActivity() {

    private val binding by inflate<ActivityViewpagerStudyBinding>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager_study)

        binding.btnViewpagerBase.setOnClickListener {
            startActivity(
                Intent(
                    MainActivity@ this,
                    ViewPagerStudy1Activity::class.java
                )
            )
        }
        binding.btnViewpagerFragment.setOnClickListener {
            startActivity(
                Intent(
                    MainActivity@ this,
                    ViewPagerStudy2Activity::class.java
                )
            )

        }

    }
}