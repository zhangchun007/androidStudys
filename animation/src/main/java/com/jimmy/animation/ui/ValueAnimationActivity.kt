package com.jimmy.animation.ui

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.jimmy.animation.R

class ValueAnimationActivity : AppCompatActivity() {
    var btn_value_animation: Button? = null
    var btn_remove_animation: Button? = null
    var btn_demo: Button? = null
    var tv_value_animation: TextView? = null

    var animator: ValueAnimator? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_animation)
        btn_value_animation = findViewById(R.id.btn_value_animation);
        btn_remove_animation = findViewById(R.id.btn_remove_animation);
        btn_demo = findViewById(R.id.btn_demo);
        tv_value_animation = findViewById(R.id.tv_value_animation);
        btn_value_animation?.setOnClickListener {
            doAnimation()
        }
        btn_remove_animation?.setOnClickListener {
            removeAnimaton()
        }
        btn_demo?.setOnClickListener {
            removeAnimaton()
        }

    }

    private fun removeAnimaton() {
        animator?.removeAllListeners()
    }

    private fun doAnimation() {
        animator = ValueAnimator.ofInt(0, 400)
        animator?.addUpdateListener {
            var curValue = it.animatedValue as Int
            var right = (curValue + tv_value_animation!!.width) as Int
            var bottom = (curValue + tv_value_animation!!.height) as Int
            tv_value_animation?.layout(curValue, curValue, right, bottom)
        }

        animator?.addListener(object : AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })

        animator?.repeatMode = ValueAnimator.REVERSE
        animator?.repeatCount = ValueAnimator.INFINITE
        animator?.setDuration(1000)
        animator?.start()

    }
}