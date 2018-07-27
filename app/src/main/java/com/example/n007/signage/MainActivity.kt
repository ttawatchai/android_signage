package com.example.n007.signage

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.example.n007.signage.model.Info.content
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    var contentList: MutableList<content> = arrayListOf()
    var uriPath = "android.resource://com.example.n007.signage/raw/"
    var count: Int = 0
    var time: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        mHandler = Handler()
        addData()
        setTime()
    }
    @SuppressLint("SetTextI18n")
    private fun setTime() {
        mRunnable = Runnable {
            val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            val currentMin = Calendar.getInstance().get(Calendar.MINUTE)
            val currentSec = Calendar.getInstance().get(Calendar.SECOND)
            tv_time.text = currentHour.toString() + ":" + currentMin.toString() + ":" + currentSec.toString()
            checkTimeTest()
            mHandler.postDelayed(
                    mRunnable, // Runnable
                    1000 // Delay in milliseconds
            )
        }
        mHandler.postDelayed(
                mRunnable, // Runnable
                1000 // Delay in milliseconds
        )
    }

    private fun addData() {
        contentList.add(content("vdo1", "vdo", "09:00:00"))
        contentList.add(content("pic1", "pic", "09:10:00"))
        contentList.add(content("pic2", "pic", "09:20:00"))
        contentList.add(content("vdo2", "vdo", "09:30:00"))
        contentList.add(content("pic3", "pic", "11:00:00"))
        contentList.add(content("vdo3", "vdo", "11:10:00"))
        contentList.add(content("pic4", "pic", "11:20:00"))
        contentList.add(content("pic5", "pic", "11:30:00"))
        contentList.add(content("pic6", "pic", "13:00:00"))
        contentList.add(content("vdo4", "vdo", "13:10:00"))
        contentList.add(content("pic7", "pic", "13:20:00"))
    }

    @SuppressLint("ResourceType")
    private fun checkTime() {
        for (i in contentList) {
            if (tv_time.text.toString() == i._time) {
                var id = resources.getIdentifier(i._name, "raw", packageName)
                if (i._type.equals("vdo")) {
                    show_img.visibility = View.GONE
                    show_vdo.visibility = View.VISIBLE
                    show_vdo.setVideoPath(uriPath + contentList.get(time)._name)
                    show_vdo.start()
                } else if (i._type.equals("pic")) {
                    show_vdo.visibility = View.GONE
                    show_img.visibility = View.VISIBLE
                    show_img.setImageResource(id)

                }
            }
        }

    }

    @SuppressLint("ResourceType")
    private fun checkTimeTest() {
        Log.d("pic", time.toString() + "count" + count)
        if (time == contentList.size) {
            time = 0
        }

        if (count == 15 || count == 0) {
            count = 0
            var id = resources.getIdentifier(contentList.get(time)._name, "raw", packageName)
            if (contentList.get(time)._type.equals("vdo")) {
                show_img.visibility = View.GONE
                show_vdo.visibility = View.VISIBLE
                show_vdo.setOnPreparedListener { mp -> mp.isLooping = true }
                show_vdo.setVideoPath(uriPath + contentList.get(time)._name)
                show_vdo.start()

            } else if (contentList.get(time)._type.equals("pic")) {
                show_vdo.visibility = View.GONE
                show_img.visibility = View.VISIBLE
                show_img.setBackgroundResource(id)
            }
            time++
        }
        count++
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setContentView(R.layout.activity_main)
    }


}

