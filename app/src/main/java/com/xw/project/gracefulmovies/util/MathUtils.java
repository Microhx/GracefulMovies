package com.xw.project.gracefulmovies.util;

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
public class MathUtils {

  public static float calculateRate(String score) {
    float currentValue = StringUtils.safeParseFloat(score);
    if(currentValue <= 0) return 0.0f;
    if(currentValue >= 10) return 5 ;
    return currentValue /2f ;
  }

}
