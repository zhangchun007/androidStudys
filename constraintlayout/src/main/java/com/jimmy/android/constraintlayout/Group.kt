package com.jimmy.constraintlayoutdemo

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import com.jimmy.android.constraintlayout.R

/**
 * @Description: Group 用于控制多个控件的可见性，先依靠 constraint_referenced_ids来绑定其它 View，之后就可以通过单独控制
 * Group 的可见性从而来间接改变绑定的 View 的可见性
 * @Author:         zhangchun
 * @CreateDate:     2021/8/23
 * @Version:        1.0
 */
class Group : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)
        findViewById<Button>(R.id.button).setOnClickListener {
            findViewById<Group>(R.id.group).visibility = View.GONE
        }
    }
}