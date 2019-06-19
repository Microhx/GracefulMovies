package com.xing.project.movie.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.xing.project.movie.util.Util

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/6/4
 *
 * version : 1.0.1
 *
 * desc :
 *
 *
 */
class MovieItemDataView(context: Context?, attributeSet: AttributeSet?) : LinearLayout(context, attributeSet) {

  constructor(context: Context?) : this(context, null)

  private var mtvTitle: TextView

  private var mTvContent: TextView

  init {
    orientation = HORIZONTAL
    gravity = Gravity.CENTER_HORIZONTAL

    mtvTitle = TextView(getContext())
    mtvTitle.setPadding(Util.dp2px(2), Util.dp2px(8), Util.dp2px(16), Util.dp2px(8))
    mtvTitle.gravity = Gravity.START + Gravity.CENTER_VERTICAL
    var params = LayoutParams(Util.dp2px(100), LayoutParams.WRAP_CONTENT)
    addView(mtvTitle, params)

    mTvContent = TextView(getContext())
    mTvContent.setPadding(Util.dp2px(4), Util.dp2px(8), Util.dp2px(4), Util.dp2px(8))

    params = LayoutParams(0, LayoutParams.WRAP_CONTENT)
    params.weight = 1f
    addView(mTvContent, params)
  }


  fun setContent(title: String, content: String?) {
    mtvTitle.text = title
    mTvContent.text = content
  }


}