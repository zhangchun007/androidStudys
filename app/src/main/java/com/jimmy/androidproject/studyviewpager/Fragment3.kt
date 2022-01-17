package com.jimmy.androidproject.studyviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jimmy.androidproject.R

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/12/25
 * @Version:        1.0
 */
class Fragment3 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout3, container, false)
    }
}