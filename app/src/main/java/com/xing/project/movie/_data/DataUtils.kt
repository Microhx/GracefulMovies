package com.xing.project.movie._data

import com.xing.project.movie.data.ao.SearchData
import com.xing.project.movie.data.ao.SearchMovieData
import com.xing.project.movie.data.ao.SearchTitleData

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/5/21
 *
 * version : 1.0.1
 *
 * desc :
 *
 *
 */
object DataUtils {


  fun getSearchTitle(count:Int) : List<SearchTitleData> {
    val list = ArrayList<SearchTitleData>()


    list.add(SearchTitleData(0, "全部"))
    for(i in 0 .. count) {
      list.add(SearchTitleData(i, "data: $i"))
    }

    return list
  }


  fun getSearchData(count:Int) : List<SearchData> {

    val list = ArrayList<SearchData>()

    for(i in 0.. count) {
      list.add(SearchData("$i"))
    }
    
    return list
  }

  fun getSearchMovieData(count : Int) : List<SearchMovieData> {
    val list = ArrayList<SearchMovieData>()

    for(i in 0..count) {
      list.add(SearchMovieData())
    }

    return list


  }

}