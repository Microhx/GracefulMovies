package com.xw.project.gracefulmovies.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.xw.project.gracefulmovies.R

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
class TestClass : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

      DataBindingUtil.setContentView<com.xw.project.gracefulmovies.databinding.ActivityTestLayoutBinding>(this, R.layout.activity_test_layout)
  }


}