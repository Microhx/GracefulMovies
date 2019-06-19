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
data class NewMovieItemData(

    var movieId:String,
    var movieName:String,
    var image:String,
    var movieType:String?,
    var score:String?,
    var mainActor:String?,
    var publishTime:String?,
    var playCount:String?,
    var transitionName:String?
)
