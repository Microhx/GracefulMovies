package com.xing.project.movie.ui.search.adapter

import com.chad.library.adapter.base.BaseViewHolder
import com.xing.project.movie.R
import com.xing.project.movie.data.ao.SearchTitleData
import com.xing.project.movie.ui.activity.adaper.BaseCommonAdapter

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
class SearchTitleAdapter(listData: List<SearchTitleData>)  : BaseCommonAdapter<SearchTitleData>(listData, R.layout.item_search_title) {


  override fun convert(helper: BaseViewHolder?, item: SearchTitleData?) {
      helper?.setText(R.id.id_content, item?.name)
      helper?.setChecked(R.id.id_content, item?.id == 4)
  }



}