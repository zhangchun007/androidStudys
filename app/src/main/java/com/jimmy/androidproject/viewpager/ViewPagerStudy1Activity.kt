package com.jimmy.androidproject.viewpager

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
class ViewPagerStudy1Activity : AppCompatActivity() {
    private var view1: View? = null
    private var view2: View? = null
    private var view3: View? = null
    private var viewList: MutableList<View> = ArrayList()
    private val binding by inflate<ActivityViewpagerStudy1Binding>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager_study1)

        view1 = layoutInflater.inflate(R.layout.layout1, null)
        view2 = layoutInflater.inflate(R.layout.layout2, null)
        view3 = layoutInflater.inflate(R.layout.layout3, null)

        viewList.add(view1 as View)
        viewList.add(view2 as View)
        viewList.add(view3 as View)

        var pagerAdapter = object : PagerAdapter() {
            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                Log.e("isViewFromObject","isViewFromObject==${ view == `object`}")
                return view == `object`
            }

            override fun getCount(): Int = viewList.size

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                container.removeView(viewList[position])
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                container.addView(viewList[position])
                return viewList[position]
            }

        }
        binding.viewPagerRoot.adapter = pagerAdapter

    }
}