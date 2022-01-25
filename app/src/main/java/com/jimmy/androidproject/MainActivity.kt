package com.jimmy.androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jimmy.androidproject.adapter.MainAdapter
import com.jimmy.androidproject.bean.Project
import com.jimmy.androidproject.databinding.ActivityMainBinding
import com.jimmy.androidproject.studycontentprovider.ContentProividerActivity
import com.jimmy.androidproject.studyviewpager.ViewPagerStudyActivity
import com.jimmy.androidproject.studyevent.EventStudyActivity
import com.jimmy.androidproject.studyfanxing.FanXingActivity

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

    fun getData(): ArrayList<Project> {
        var data = ArrayList<Project>()
        data.add(Project().apply {
            this.type = "viewpager"
            this.content = "viewpager"
        })
        data.add(Project().apply {
            this.type = "contentProvider"
            this.content = "contentProvider"
        })
        data.add(Project().apply {
            this.type = "event"
            this.content = "event事件"
        })
        data.add(Project().apply {
            this.type = "fanxing"
            this.content = "泛型"
        })

        return data
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        var project = adapter?.data?.get(position) as Project
        when (project.type) {
            "contentProvider" -> startActivity(
                Intent(
                    MainActivity@ this,
                    ContentProividerActivity::class.java
                )
            )

            "viewpager" -> startActivity(
                Intent(
                    MainActivity@ this,
                    ViewPagerStudyActivity::class.java
                )
            )

            "event" -> startActivity(
                Intent(
                    MainActivity@ this,
                    EventStudyActivity::class.java
                )
            )
            "fanxing" -> startActivity(
                Intent(
                    MainActivity@ this,
                    FanXingActivity::class.java
                )
            )
            else -> ""
        }
    }


}