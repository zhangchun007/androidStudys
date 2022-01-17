package com.jimmy.androidproject.studyevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Description:
 * @Author: zhangchun
 * @CreateDate: 2021/12/26
 * @Version: 1.0
 */
public class EventGroups extends FrameLayout {
    public EventGroups(@NonNull Context context) {
        super(context);
    }

    public EventGroups(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EventGroups(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("jimmy", "EventGroups--dispatchTouchEvent-- "+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("jimmy", "EventGroups--onInterceptTouchEvent-- "+ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("jimmy", "EventGroups--onTouchEvent-- "+event.getAction());
        return true;
    }
}
