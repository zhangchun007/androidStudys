package com.jimmy.animation.valueanimator

import android.animation.TimeInterpolator

/**
 * @Description:    倒序加速器
 * @Author:         zhangchun
 * @CreateDate:     2024/6/30
 * @Version:        1.0
 */
class MyInterpolator :TimeInterpolator {
    //插值器返回 input的小数值表示的是当前动画的数值进度
    override fun getInterpolation(input: Float): Float {
       return 1-input
    }
}