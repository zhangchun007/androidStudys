package com.jimmy.animation.valueanimator

import android.animation.TypeEvaluator

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2024/6/30
 * @Version:        1.0
 */
class CharEvaluator : TypeEvaluator<Character> {
    override fun evaluate(fraction: Float, startValue: Character, endValue: Character): Character {
        var startInt = startValue.charValue()
        var endInt = endValue.charValue()
        var curInt = (startInt + (fraction * (endInt - startInt)).toInt())
        return curInt as Character
    }
}