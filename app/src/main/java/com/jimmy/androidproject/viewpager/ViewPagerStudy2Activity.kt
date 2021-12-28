package com.jimmy.androidproject.viewpager

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.jimmy.androidproject.R
import com.jimmy.androidproject.databinding.ActivityViewpagerStudy1Binding
import com.jimmy.androidproject.databinding.ActivityViewpagerStudyBinding
import com.jimmy.androidproject.inflate
import java.util.logging.Logger
import kotlin.math.log

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/12/25
 * @Version:        1.0
 */
class ViewPagerStudy2Activity : AppCompatActivity() {
    private var view1: View? = null
    private var view2: View? = null
    private var view3: View? = null
    private var viewList: MutableList<Fragment> = ArrayList()
    private val binding by inflate<ActivityViewpagerStudy1Binding>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager_study1)

        view1 = layoutInflater.inflate(R.layout.layout1, null)
        view2 = layoutInflater.inflate(R.layout.layout2, null)
        view3 = layoutInflater.inflate(R.layout.layout3, null)

        viewList.add(Fragment1())
        viewList.add(Fragment2())
        viewList.add(Fragment3())

        var pagerAdapter = FragAdapter(supportFragmentManager,viewList)
        binding.viewPagerRoot.adapter = pagerAdapter

    }
}