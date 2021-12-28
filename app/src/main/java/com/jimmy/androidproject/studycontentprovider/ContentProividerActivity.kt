package com.jimmy.androidproject.studycontentprovider

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jimmy.androidproject.R
import com.jimmy.androidproject.databinding.ActivityContentProviderBinding
import com.jimmy.androidproject.inflate

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/6/30
 * @Version:        1.0
 */
class ContentProividerActivity : AppCompatActivity() {

    private val binding by inflate<ActivityContentProviderBinding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)

        binding.btnReadContactList.setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }
    }
}