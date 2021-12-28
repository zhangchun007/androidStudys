package com.jimmy.customview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jimmy.androidproject.adapter.MainAdapter
import com.jimmy.androidproject.bean.Project
import com.jimmy.customview.databinding.ActivityMainBinding
import com.jimmy.customview.ui.*

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private val binding by inflate<ActivityMainBinding>()
    private var mainAdapter: MainAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.recycler.layoutManager = LinearLayoutManager(this)
        var list = getData()
        mainAdapter = MainAdapter(data = list)
        binding.recycler.adapter = mainAdapter
        mainAdapter?.setOnItemClickListener(this)

    }

    private fun getData(): ArrayList<Project> {
        var data = ArrayList<Project>()
        data.add(Project().apply {
            this.type = "customview_1"
            this.content = "概述及基本几何图形绘制"
        })
        data.add(Project().apply {
            this.type = "customview_2"
            this.content = "路径(Path)"
        })
        data.add(Project().apply {
            this.type = "customview_3"
            this.content = "区域(Range)"
        })
        data.add(Project().apply {
            this.type = "customview_4"
            this.content = "文字"
        })
        data.add(Project().apply {
            this.type = "customview_6"
            this.content = "drawText()详解"
        })
        data.add(Project().apply {
            this.type = "customview_5"
            this.content = "canvas变换与操作"
        })
        return data
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        var project = adapter?.data?.get(position) as Project
        when (project.type) {
            "customview_1" -> startActivity(
                Intent(
                    MainActivity@ this,
                    CustomViewActivity1::class.java
                )
            )
            "customview_2" -> startActivity(
                Intent(
                    MainActivity@ this,
                    CustomViewActivity2::class.java
                )
            )
            "customview_3" -> startActivity(
                Intent(
                    MainActivity@ this,
                    CustomViewActivity3::class.java
                )
            )
            "customview_4" -> startActivity(
                Intent(
                    MainActivity@ this,
                    CustomViewActivity4::class.java
                )
            )
            "customview_5" -> startActivity(
                Intent(
                    MainActivity@ this,
                    CustomViewActivity5::class.java
                )
            )
            "customview_6" -> startActivity(
                Intent(
                    MainActivity@ this,
                    CustomViewActivity6::class.java
                )
            )

            else -> ""
        }
    }
}