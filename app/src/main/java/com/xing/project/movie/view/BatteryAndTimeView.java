package com.xing.project.movie.view;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/5/16
 *
 * version : 1.0.1
 *
 * desc : 播放器右上方 显示手机电量与 当前时间组件的 view
 */
public class BatteryAndTimeView extends View {

  public BatteryAndTimeView(Context context) {
    this(context,null);
  }

  public BatteryAndTimeView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs,0);
  }

  public BatteryAndTimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initSetting();
  }

  private void initSetting() { }


}
