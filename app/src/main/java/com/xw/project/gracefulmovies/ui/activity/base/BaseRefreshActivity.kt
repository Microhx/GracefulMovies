package com.xw.project.gracefulmovies.ui.activity.base

import com.xw.project.gracefulmovies.R
import com.xw.project.gracefulmovies.databinding.ActivityRefreshLayoutBinding

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/5/21
 *
 * version : 1.0.1
 *
 * desc :
 *
 *
 */
 abstract class BaseRefreshActivity : BaseActivity<ActivityRefreshLayoutBinding>() {


  override fun contentLayoutRes(): Int {
    return R.layout.activity_refresh_layout
  }

  override fun afterSetContentView() {

  }



}