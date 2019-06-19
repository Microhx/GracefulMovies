package com.xing.project.movie.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.xing.project.movie.entity.NewMovieDetailData

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
 *
 *
 */
class MovieDataView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

  init {
    orientation = VERTICAL
  }

  fun addData(data: NewMovieDetailData?) {
    data?.apply {
      addInnerView("电影名称", this.movieName)
      addInnerView("电影译名", this.transitionName)
      addInnerView("电影年代", this.movieDecade)
      addInnerView("发布时间", this.publishTime)
      addInnerView("电影评分",this.score)
      addInnerView("电影时长",this.movieTime)
      addInnerView("上线地区", this.movieLocation)
      addInnerView("电影语言", this.language)
      addInnerView("电影字幕", this.movieCaption)
      addInnerView("上映时间",this.showTime)
      addInnerView("豆瓣评分",this.doupanScore)
      addInnerView("imdb评分", this.imdbScore)
      addInnerView("文件格式", this.movieFormat)
      addInnerView("视频尺寸",this.movieMeasure)
      addInnerView("视频大小",this.movieSize)
      addInnerView("推荐指数",this.recommend)
      addInnerView("热门指数",this.hot)
    }
  }


  private fun addInnerView(title:String, content:String?) {
    val innerView =  MovieItemDataView(context)
    innerView.setContent(title, content)
    addView(innerView)
  }




}