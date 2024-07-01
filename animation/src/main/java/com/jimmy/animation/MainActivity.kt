package com.jimmy.animation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jimmy.animation.valueanimator.ValueAnimationActivity
import com.jimmy.animation.ui.ViewAnimationActivity

class MainActivity : AppCompatActivity() {
    var btn_view_animation: Button? = null;
    var btn_value_animation: Button? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_view_animation = findViewById(R.id.btn_view_animation);
        btn_value_animation = findViewById(R.id.btn_value_animation);

        btn_view_animation?.setOnClickListener {
            var intent=Intent(MainActivity@this, ViewAnimationActivity::class.java)
            startActivity(intent)
        }

        btn_value_animation?.setOnClickListener {
            var intent=Intent(MainActivity@this, ValueAnimationActivity::class.java)
            startActivity(intent)
        }
    }
}