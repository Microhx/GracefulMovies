package com.xw.project.gracefulmovies.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
public class CommonUtils {

  public static boolean checkCollectionSize(Collection<?> collection , int size) {
    return null != collection && collection.size() >= size;
  }


  public static <T> List<T> getSafeArrayList(List<T> list) {
    return null == list ? new ArrayList<>() : list;
  }

}
