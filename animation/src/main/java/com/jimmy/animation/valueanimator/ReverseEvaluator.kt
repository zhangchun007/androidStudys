package com.jimmy.animation.valueanimator

import android.animation.TypeEvaluator
import android.util.Log.e

/**
 * @Description:    Evaluator 就是根据Interpolator返回的动画进度值计算得到数值动画当前的计算结果
 * @Author:         zhangchun
 * @CreateDate:     2024/6/30
 * @Version:        1.0
 */
class ReverseEvaluator : TypeEvaluator<Int> {
    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        var startInt = startValue
        return (endValue - fraction * (endValue - startInt)) as Int
    }
}