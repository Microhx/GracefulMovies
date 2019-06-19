package com.xing.project.movie.util;

import com.xing.project.movie.data.db.entity.MovieDataItemEntity;
import java.util.ArrayList;
import java.util.List;

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
public class DataUtils {

  public static List<MovieDataItemEntity> getMovieData() {
    List<MovieDataItemEntity> list = new ArrayList<>();
    list.add(new MovieDataItemEntity("片名","比悲傷更悲傷的故事"));
    list.add(new MovieDataItemEntity("译名","比悲伤更悲伤的故事/More Than Blue"));
    list.add(new MovieDataItemEntity("年代","2018"));
    list.add(new MovieDataItemEntity("产地","台湾"));
    list.add(new MovieDataItemEntity("类别","爱情"));
    list.add(new MovieDataItemEntity("语言","韩语普通话"));
    list.add(new MovieDataItemEntity("字幕","中文字幕"));
    list.add(new MovieDataItemEntity("评分","4.8"));
    list.add(new MovieDataItemEntity("类型","爱情片"));
    list.add(new MovieDataItemEntity("发布时间","2019-05-15"));
    list.add(new MovieDataItemEntity("上映日期","2019-03-14(中国大陆)/2018-11-30(台湾)"));
    list.add(new MovieDataItemEntity("豆瓣评分","4.8/10 from 73341 users"));
    list.add(new MovieDataItemEntity("IMDb评分","5.5/10 from 492 users"));
    list.add(new MovieDataItemEntity("文件格式","x264 + ACC"));
    list.add(new MovieDataItemEntity("视频尺寸","1280 x 720"));
    list.add(new MovieDataItemEntity("文件大小","1750 MB"));
    list.add(new MovieDataItemEntity("片　　长","106 Mins"));
    list.add(new MovieDataItemEntity("导　　演","林孝谦 Hsiao Chien Lin"));
    list.add(new MovieDataItemEntity("主　　演","陈意涵 Ivy Chen、\n" +
                                                             "刘以豪 Jasper Liu、\n" +
                                                            " 张书豪 Bryan Shu-Hao Chang\n" +
                                                            " 陈庭妮 Annie Chen\n" +
                                                            " 吴映洁 Gemma Wu\n" +
                                                            " 禾浩辰 Bruce Hung\n" +
                                                            " 游大庆 Da-Ching\n" +
                                                            " 石知田 Chih Tian Shih\n" +
                                                            " 黄丽玲 A-Lin\n" +
                                                            " 姚爱宁 Pi Pi Yao"));
    list.add(new MovieDataItemEntity("观看次数","290834次"));


    return list;

  }


}
