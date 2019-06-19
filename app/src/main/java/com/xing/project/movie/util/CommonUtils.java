package com.xing.project.movie.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * author: Java
 * <p>
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 * <p>
 * date : 2019/6/10
 * <p>
 * version : 1.0.1
 * <p>
 * desc :
 */
public class CommonUtils {

    public static boolean checkCollectionSize(Collection<?> collection, int size) {
        return null != collection && collection.size() >= size;
    }

    public static boolean checkCollection(Collection<?> collection) {
        return null != collection && !collection.isEmpty();
    }


    public static <T> List<T> getSafeArrayList(List<T> list) {
        return null == list ? new ArrayList<>() : list;
    }

}
