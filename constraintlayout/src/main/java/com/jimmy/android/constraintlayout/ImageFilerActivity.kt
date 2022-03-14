package com.jimmy.constraintlayoutdemo

import android.os.Build
import android.os.Bundle
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.jimmy.android.constraintlayout.R

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2022/3/14
 * @Version:        1.0
 */
class ImageFilerActivity : AppCompatActivity() {
    var imageView1 : ImageFilterView? =null
    var imageView2 : ImageFilterView? =null
    var imageView3 : ImageFilterView? =null
    var imageView4 : ImageFilterView? =null
    var imageView5 : ImageFilterView? =null
    var imageView6 : ImageFilterView? =null
    var imageView7 : ImageFilterView? =null
    var seekBar : AppCompatSeekBar? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagefilter)
        imageView1=findViewById(R.id.imageView1)
        imageView2=findViewById(R.id.imageView2)
        imageView3=findViewById(R.id.imageView3)
        imageView4=findViewById(R.id.imageView4)
        imageView5=findViewById(R.id.imageView5)
        imageView6=findViewById(R.id.imageView6)
        imageView7=findViewById(R.id.imageView7)
        seekBar=findViewById(R.id.seekBar)
        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val realProgress = (progress / 100.0).toFloat()

                    imageView1?.saturation = realProgress * 20
                    imageView2?.brightness = 1 - realProgress

                    imageView3?.warmth = realProgress * 20
                    imageView4?.contrast = realProgress * 2

                    imageView5?.round = realProgress * 40
                    imageView6?.roundPercent = realProgress

                    imageView7?.crossfade = realProgress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }


}