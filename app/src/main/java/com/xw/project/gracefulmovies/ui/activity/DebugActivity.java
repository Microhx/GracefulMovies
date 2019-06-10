package com.xw.project.gracefulmovies.ui.activity;

import android.view.View;
import com.xw.project.gracefulmovies.R;
import com.xw.project.gracefulmovies.ui.activity.base.NewBaseActivity;
import tech.linjiang.pandora.Pandora;

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/6/10
 *
 * version : 1.0.1
 *
 * desc :
 */
public class DebugActivity extends NewBaseActivity {

  @Override public int getContentLayout() {
    return R.layout.activity_debug;
  }


  public void openTestConfig(View v) {
    Pandora.get().open();
  }
}
