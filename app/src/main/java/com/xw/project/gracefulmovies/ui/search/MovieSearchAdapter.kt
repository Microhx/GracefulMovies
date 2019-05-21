package com.xw.project.gracefulmovies.ui.search

import com.chad.library.adapter.base.BaseViewHolder
import com.xw.project.gracefulmovies.R
import com.xw.project.gracefulmovies._data.DataUtils
import com.xw.project.gracefulmovies.data.ao.SearchData
import com.xw.project.gracefulmovies.ui.activity.adaper.BaseCommonAdapter

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
class MovieSearchAdapter : BaseCommonAdapter<SearchData>(DataUtils.getSearchData(20),R.layout.item_search_movie) {

  override fun convert(helper: BaseViewHolder?, item: SearchData?) {

  }


}