package com.xing.project.movie.ui.activity.base

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.xing.project.movie.R
import kotlinx.android.synthetic.main.new_base_activity_layout.*

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
abstract class NewBaseActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    receiveIntent(intent)

    setContentView(R.layout.new_base_activity_layout)
    id_hx_state_view.addContentView(getContentLayout())

    initDatas()
  }

  open fun receiveIntent(intent: Intent?) {}

  abstract fun getContentLayout(): Int

  open fun initDatas(){}

  protected fun showLoading() {
    id_hx_state_view.showLoading()
  }

  protected fun showError() {
    id_hx_state_view.showError()
  }

  protected fun showEmpty(){
    id_hx_state_view.showEmpty()
  }

  protected fun showContent(){
    id_hx_state_view.showContentLayout()
  }

  /**
   * 检测系统版本并使状态栏全透明
   */
  protected fun transparentStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      val window = window

      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
      window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
      window.statusBarColor = Color.TRANSPARENT
    }
  }




//  final override fun bindingViewByOldWay(): Boolean = false
//
//  override fun afterSetContentView() {
//    mBinding = DataBindingUtil.setContentView(this, R.layout.new_base_activity_layout)
//    stateVew = findViewById(R.id.id_hx_state_view)
//    stateVew.addContentView(contentLayoutRes())
//
//    initViewAndData()
//  }
//
//  protected fun initViewAndData() {
//
//  }

}