package com.jimmy.androidproject.viewpager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.jimmy.androidproject.R
import com.jimmy.androidproject.databinding.ActivityViewpagerStudy1Binding
import com.jimmy.androidproject.inflate

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/12/25
 * @Version:        1.0
 */
class Fragment1 : Fragment() {

    private var view1: View? = null
    private var view2: View? = null
    private var view3: View? = null
    private var viewList: MutableList<View> = ArrayList()
    private var viewPagerContent: ViewPager? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.layout1, container, false)
        viewPagerContent = view?.findViewById(R.id.view_pager_content)


        view1 = layoutInflater.inflate(R.layout.layout11, null)
        view2 = layoutInflater.inflate(R.layout.layout2, null)
        view3 = layoutInflater.inflate(R.layout.layout3, null)

        viewList.add(view1 as View)
        viewList.add(view2 as View)
        viewList.add(view3 as View)

        var pagerAdapter = object : PagerAdapter() {
            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                Log.e("isViewFromObject", "isViewFromObject==${view == `object`}")
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
        viewPagerContent?.adapter = pagerAdapter
        return view
    }
}