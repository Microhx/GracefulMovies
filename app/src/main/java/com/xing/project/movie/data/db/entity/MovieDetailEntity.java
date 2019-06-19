package com.xing.project.movie.data.db.entity;

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/5/16
 *
 * version : 1.0.1
 *
 * desc : 电影数据详情
 */
public class MovieDetailEntity {

  //电影ID
  public long movieId;

  public String name ;

  public String score ;

  //类型
  public String type;

  public String publishTime;

  //翻译名
  public String foreignName;

  //电影年代
  public String movieTime ;

  //电影产地
  public String productArea;

  //电影语言
  public String language ;

  //上映时间
  public String releaseTime ;

  //豆瓣评分
  public String dobanScore;

  //IMDb评分
  public String IMDbScore;

  //电影尺寸大小
  public String movieSize ;

  public String fileSize;

  //电影时长
  public String timeLength;

  //导演
  private String director ;

  //主演
  private String actors ;

  //观看次数
  public String watchTime;


}

