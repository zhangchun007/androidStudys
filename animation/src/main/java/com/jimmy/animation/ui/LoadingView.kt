package com.jimmy.animation.ui

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2024/6/29
 * @Version:        1.0
 */
class LoadingView(context: Context, attrs: AttributeSet, defStyleAttr: Int = 0) :
    AppCompatImageView(context, attrs, defStyleAttr) {

    var mTop: Int = 0

    init {
        var valueAnimator = ValueAnimator.ofInt(0, 100, 0)
        valueAnimator.repeatMode = ValueAnimator.RESTART
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.duration = 2000
        valueAnimator.interpolator = AccelerateInterpolator()

        valueAnimator.addUpdateListener {
            var dx = it.animatedValue as Int
            top = (mTop - dx)
        }
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
        mTop = t
    }

}