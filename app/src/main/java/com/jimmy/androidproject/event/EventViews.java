package com.jimmy.androidproject.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Description:
 * @Author: zhangchun
 * @CreateDate: 2021/12/26
 * @Version: 1.0
 */
public class EventViews extends View {
    public EventViews(Context context) {
        super(context);
    }

    public EventViews(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EventViews(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("jimmy", "view--dispatchTouchEvent-- "+event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.e("jimmy", "view--onTouchEvent--"+event.getAction());
        return super.onTouchEvent(event);
    }
}
