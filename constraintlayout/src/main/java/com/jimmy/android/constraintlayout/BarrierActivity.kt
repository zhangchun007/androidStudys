package com.jimmy.constraintlayoutdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jimmy.android.constraintlayout.R

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/8/23
 * @Version:        1.0
 *
 * 很多时候我们都会遇到控件的宽高值随着其包含的数据的多少而改变的情况，而此时如果有多个控件之间是相互约束的话，就比较难来设定各个控件间的约束关系了，而 Barrier（屏障）就是用于解决这种情况。
 * Barrier 和 GuideLine 一样是一个虚拟的 View，对界面是不可见的，只是用于辅助布局
 */
class BarrierActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barrier)
    }
}