package com.jimmy.androidproject.event

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.jimmy.androidproject.R

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2021/12/26
 * @Version:        1.0
 */
class EventStudyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.e("jimmy", "EventStudentActivity--dispatchTouchEvent--${event?.action} ")
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e("jimmy", "EventStudentActivity--onTouchEvent--MotionEvent.ACTION_DOWN ")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e("jimmy", "EventStudentActivity--onTouchEvent--MotionEvent.ACTION_MOVE ")

            }
            MotionEvent.ACTION_UP -> {
                Log.e("jimmy", "EventStudentActivity--onTouchEvent--MotionEvent.ACTION_UP ")
            }
        }
        return super.onTouchEvent(event)
    }
}