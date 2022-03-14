package com.jimmy.constraintlayoutdemo

import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Layer
import com.jimmy.android.constraintlayout.R

/**
 * @Description: Layer 作为一种新的辅助工具，可以在多个视图上创建一个虚拟的图层 (layer)，和 Flow 不同，它并不会对视图进行布局，
 * 而是对多个视图同时进行变换 (transformation) 操作。如果想对多个视图整体进行旋转 (rotate)、平移 (translate) 或缩放 (scale) 操作，那么 Layer 将会是最佳的选择

 * @Author:         zhangchun
 * @CreateDate:     2021/8/23
 * @Version:        1.0
 */
class LayerActivity : AppCompatActivity() {
    override fun setContentView(view: View?) {
        setContentView(R.layout.activity_layer)
        findViewById<Button>(R.id.button).setOnClickListener {
            findViewById<Layer>(R.id.layer).rotation = 45f
            findViewById<Layer>(R.id.layer).translationY = 100f
            findViewById<Layer>(R.id.layer).translationX = 100f
        }
    }
}