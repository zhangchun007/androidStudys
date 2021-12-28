package com.jimmy.androidproject.adapter

import android.widget.Button
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jimmy.androidproject.bean.Project
import com.jimmy.customview.R

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/6/30
 * @Version:        1.0
 */
class MainAdapter(layoutId: Int = R.layout.item_main, data: ArrayList<Project>? = null) :
    BaseQuickAdapter<Project, BaseViewHolder>(layoutId, data) {
    override fun convert(holder: BaseViewHolder, item: Project) {
        val tv_content = holder.getView<TextView>(R.id.tv_content)
        tv_content.text = item.content
    }

}