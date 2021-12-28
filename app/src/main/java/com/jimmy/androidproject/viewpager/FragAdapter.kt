package com.jimmy.androidproject.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/12/25
 * @Version:        1.0
 */
class FragAdapter(private var fm: FragmentManager, private val data: MutableList<Fragment>) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return data[position]
    }

    override fun getCount(): Int = data.size
}