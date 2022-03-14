package com.hencoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jimmy.android.constraintlayout.R

/**
 * Flow 和 Layer 都是 ConstraintHelper 的子类，这两者都属于辅助布局的工具类，ConstraintLayout 也开放了 ConstraintHelper 交由开发者自己去进行自定义
 */
class CircularReveal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_reveal)
    }
}
