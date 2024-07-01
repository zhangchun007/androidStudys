package com.jimmy.animation.valueanimator

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.widget.AppCompatImageView
import com.jimmy.animation.R

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2024/6/29
 * @Version:        1.0
 */
class LoadingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    AppCompatImageView(context, attrs, defStyleAttr) {

    var mTop: Int = 0

    //当前动画图片索引
    var mCurrentImgIndex=0
    val mImageCount=3
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

        valueAnimator.addListener(object :AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
                setImageDrawable(getResources() .getDrawable(R.drawable.pic_1));
            }

            override fun onAnimationEnd(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
                mCurrentImgIndex++
                when(mCurrentImgIndex%mImageCount){
                    0->{
                        setImageDrawable(resources.getDrawable(R.drawable.pic_1))
                    }
                    1->{
                        setImageDrawable(resources.getDrawable(R.drawable.pic_2))
                    }
                    2->{
                        setImageDrawable(resources.getDrawable(R.drawable.pic_3))
                    }
                    else->{

                    }
                }
            }

        })

        valueAnimator.start()
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
        mTop = t
    }

}