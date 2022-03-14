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
 * Flow 是一种新的虚拟布局，它专门用来构建链式排版效果，当出现空间不足的情况时能够自动换行，甚至是自动延展到屏幕的另一区域。
 * 当需要对多个元素进行链式布局，但不确定在运行时布局空间的实际大小是多少时 Flow 对你来说就非常有用。
 * 你可以使用 Flow 来实现让布局随着应用屏幕尺寸的变化 (比如设备发生旋转后出现的屏幕宽度变化) 而动态地进行自适应。
 * 此外，Flow 是一种虚拟布局，并不会作为视图添加到视图层级结构中，而是仅仅引用其它视图来辅助它们在布局系统中完成各自的布局功能
 *
 */
class FlowActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
    }
}