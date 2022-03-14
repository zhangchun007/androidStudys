package com.jimmy.constraintlayoutdemo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.jimmy.android.constraintlayout.R

/**
 * Layer 是对 ConstraintLayout 内的一部分控件做动画变换，ConstraintSet 则是用于对 ConstraintLayout 整体进行一次动画变换
ConstraintSet 可以理解为 ConstraintLayout 对其所有子控件的约束规则的集合。
在不同的交互规则下，我们可能需要改变 ConstraintLayout 内的所有子控件的约束条件，即子控件的位置需要做一个大调整，ConstraintSet 就用于实现平滑地改变子控件的位置
例如，我们需要在不同的场景下使用两种不同的布局形式，先定义好这两种布局文件，其中子 View 的 Id 必须保持一致，View 的约束条件则可以随意设置。
然后在代码中通过 ConstraintSet 来加载这两个布局文件的约束规则，apply 给 ConstraintLayout 后即可平滑地切换两种布局效果

 */
class ConstraintSetX : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_start)
    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun onClick(view: View) {
        val constraintLayout = view as ConstraintLayout

        val constraintSet = ConstraintSet().apply {
            isForceId = false
            clone(this@ConstraintSetX,
                R.layout.activity_constraint_end
            )
        }
        TransitionManager.beginDelayedTransition(constraintLayout)
        constraintSet.applyTo(constraintLayout)
    }



//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_constraint_set)
//    }
//
//
//    fun onClick(view: View) {
//        val constraintLayout = view as ConstraintLayout
//        val constraintSet = ConstraintSet().apply {
//            clone(constraintLayout)
//            connect(
//                R.id.twitter,
//                ConstraintSet.BOTTOM,
//                ConstraintSet.PARENT_ID,
//                ConstraintSet.BOTTOM
//            )
//        }
//        constraintSet.applyTo(constraintLayout)
//    }

//
}
