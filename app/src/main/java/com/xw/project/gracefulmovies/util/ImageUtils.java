package com.xw.project.gracefulmovies.util;

import android.widget.ImageView;
import com.bumptech.glide.Glide;

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
public class ImageUtils {

  public static void loadImage(String url, ImageView iv) {
    Glide.with(iv.getContext()).load(url).into(iv);


  }
}
