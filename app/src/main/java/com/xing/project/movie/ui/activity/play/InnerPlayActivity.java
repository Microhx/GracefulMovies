package com.xing.project.movie.ui.activity.play;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.xing.project.movie.R;
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
public class InnerPlayActivity extends AppCompatActivity {

  StandardGSYVideoPlayer videoPlayer;
  OrientationUtils orientationUtils ;

  private String movieUrl;


  public static void start(Context context, String movieUrl) {
    if(context instanceof  PlayerDelegateActivity) {
      context.startActivity(new Intent(context,  InnerPlayActivity.class).putExtra("movieUrl",movieUrl));
    }else {
      Logy.e("enter Illegal way ");
    }
  }


  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    receiveMovieInfo();

    setContentView(R.layout.activity_for_player);

    initFullScreen();


    init();
  }

  private void initFullScreen() {
    View decorView = getWindow().getDecorView();
    int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
    decorView.setSystemUiVisibility(option);

  }

  private void receiveMovieInfo() {
    movieUrl = (getIntent() == null) ? "" : getIntent().getStringExtra("movieUrl") ;
  }

  private void init() {
    videoPlayer =  findViewById(R.id.video_player);

    //String source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
    String source1 = "http://image.anglecat.com/SPIDER-MAN%20FAR%20FROM%20HOME%20-%20Official%20Trailer.mp4";

    videoPlayer.setUp(source1, true, "蜘蛛侠回归");

    //增加封面
    ImageView imageView = new ImageView(this);
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    imageView.setImageResource(R.drawable.ic_play);
    videoPlayer.setThumbImageView(imageView);


    videoPlayer.setDialogProgressColor(Color.parseColor("#FFFFFF"), Color.parseColor("#88FFFFFF"));

    //根据是否全屏
    videoPlayer.setAutoFullWithSize(true);

    //增加title
    videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
    //设置返回键
    videoPlayer.getBackButton().setVisibility(View.VISIBLE);
    //设置旋转
    orientationUtils = new OrientationUtils(this, videoPlayer);


    //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
    videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        orientationUtils.resolveByClick();
      }
    });
    //是否可以滑动调整
    videoPlayer.setIsTouchWiget(true);
    //设置返回按键功能
    videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });
    videoPlayer.startPlayLogic();
  }


  @Override
  protected void onPause() {
    super.onPause();
    videoPlayer.onVideoPause();
  }

  @Override
  protected void onResume() {
    super.onResume();
    videoPlayer.onVideoResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    GSYVideoManager.releaseAllVideos();
    if (orientationUtils != null)
      orientationUtils.releaseListener();
  }

  @Override
  public void onBackPressed() {
    //先返回正常状态
    if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
      videoPlayer.getFullscreenButton().performClick();
      return;
    }
    //释放所有
    videoPlayer.setVideoAllCallBack(null);
    super.onBackPressed();
  }

}
