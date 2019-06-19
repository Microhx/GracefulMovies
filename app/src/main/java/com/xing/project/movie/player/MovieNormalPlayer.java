package com.xing.project.movie.player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewParent;
import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;
import com.xing.project.movie.util.Logy;

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/5/16
 *
 * version : 1.0.1
 *
 * desc :
 */
public class MovieNormalPlayer extends NormalGSYVideoPlayer {

  public MovieNormalPlayer(Context context, Boolean fullFlag) {
    super(context, fullFlag);
  }

  public MovieNormalPlayer(Context context) {
    super(context);
  }

  public MovieNormalPlayer(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public boolean isInEditMode() {

    Logy.i("share in editMode: " + mLoadingProgressBar);
    if(null == mLoadingProgressBar) return false;

    ViewParent viewParent = mLoadingProgressBar.getParent();
    Logy.i("share in editMode : " + viewParent);

    return false;
  }



}
