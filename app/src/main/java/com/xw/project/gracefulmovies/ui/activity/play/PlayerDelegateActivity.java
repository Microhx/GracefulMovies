package com.xw.project.gracefulmovies.ui.activity.play;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.xw.project.gracefulmovies.util.Logy;

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/5/16
 *
 * version : 1.0.1
 *
 * desc : 电影播放前的类 作为逻辑的代理层
 *        1. 是否有广告
 *        2. 用户观看的时长
 *        3. 用户当前的等级
 *        ......
 *
 *      所有的逻辑应该在此Activity中判断
 *
 *      如果需要更换播放器，则直接在本Activity中更改
 */
public class PlayerDelegateActivity extends AppCompatActivity {

  private String movieUrl;

  public static void start(Context context, String movieUrl) {
    context.startActivity(new Intent(context, PlayerDelegateActivity.class).putExtra("movieUrl",movieUrl));
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    receiveMovieInfo();
    super.onCreate(savedInstanceState);

    checkUserLogic();
  }

  private void receiveMovieInfo() {
    movieUrl = (getIntent() == null) ? "" : getIntent().getStringExtra("movieUrl");
  }

  /**
   * 检查用户的行为
   */
  private void checkUserLogic() {
    if(TextUtils.isEmpty(movieUrl)) {
      Logy.e("movieUrl is empty");
      finish();
      return;
    }

    InnerPlayActivity.start(this,movieUrl);
    finish();
  }

}
