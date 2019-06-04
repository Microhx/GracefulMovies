package com.xw.project.gracefulmovies.util;

import android.text.TextUtils;

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
 */
public class StringUtils {

  public static String[] parseTag(String tagStr) {
    if (TextUtils.isEmpty(tagStr)) return new String[] {};
    return tagStr.split("/");
  }

  public static float safeParseFloat(String floatValue) {
    if (TextUtils.isEmpty(floatValue)) return 0.0f;
    try {
      return Float.parseFloat(floatValue);
    } catch (NumberFormatException e) {
      return 0.0f;
    }
  }

}
