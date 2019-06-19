package com.xing.project.movie.entity

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
data class NewMovieDetailData(
    var id:Long,
    var movieId:String,
    var score:String?,
    var publishTime:String?,
    var transitionName:String?,
    var movieName:String?,
    var movieDecade:String?,
    var movieLocation:String?,
    var language:String?,
    var movieCaption:String?,
    var showTime:String?,
    var doupanScore:String?,
    var imdbScore:String?,
    var movieFormat:String?,
    var movieMeasure:String?,
    var movieSize:String?,
    var movieDirector:String?,
    var movieActors:String?,
    var description:String?,
    var image:String?,
    var contentImage:String?,
    var movieType:String?,
    var movieTime:String? ,
    var recommend:String?, //推荐指数
    var hot:String?  //热门指数

)