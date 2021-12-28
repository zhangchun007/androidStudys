package com.jimmy.androidproject.adapter

import android.widget.Button
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jimmy.androidproject.R
import com.jimmy.androidproject.bean.ContactsInfo
import com.jimmy.androidproject.bean.Project

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/6/30
 * @Version:        1.0
 */
class ContactsAdapter(layoutId: Int = R.layout.item_contacts, data: ArrayList<ContactsInfo>? = null) :
    BaseQuickAdapter<ContactsInfo, BaseViewHolder>(layoutId, data) {
    override fun convert(holder: BaseViewHolder, item: ContactsInfo) {
        val tvName = holder.getView<TextView>(R.id.tv_name)
        tvName.text = item.name

        val tvPhone = holder.getView<TextView>(R.id.tv_phone)
        tvPhone.text = item.phoneNumber
    }

}