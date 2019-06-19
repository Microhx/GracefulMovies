package com.xing.project.movie.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019-06-17
 *
 * version :
 *
 * desc :
 */
class NoScrollableViewPager(context: Context, attributes: AttributeSet?) : ViewPager(context, attributes) {

    constructor(context: Context) : this(context,null)

    private var noScroll = false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return  noScroll || super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return noScroll ||  super.onTouchEvent(ev)
    }

}