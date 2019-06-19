package com.xing.project.movie.ui.search.adapter

import com.chad.library.adapter.base.BaseViewHolder
import com.xing.project.movie.R
import com.xing.project.movie.entity.NewMovieItemData
import com.xing.project.movie.ui.activity.adaper.BaseCommonAdapter
import com.xing.project.movie.util.ImageUtils

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/5/21
 *
 * version : 1.0.1
 *
 * desc : 电影类型搜索 适配器
 *
 *
 */
class MovieDataSearchAdapter : BaseCommonAdapter<NewMovieItemData>(ArrayList<NewMovieItemData>(), R.layout.item_search_movie) {


  override fun convert(helper: BaseViewHolder, item: NewMovieItemData?) {
    ImageUtils.loadImage(item?.image, helper.getView(R.id.id_image))
    helper.setText(R.id.id_tv_content, item?.movieName)
    helper.setText(R.id.id_tv_foreign_name, item?.transitionName)
    helper.setText(R.id.id_tv_type, item?.movieType)
    helper.setText(R.id.id_tv_time, item?.publishTime)
    helper.setText(R.id.id_tv_count, "播放次数:${item?.playCount}")

  }


}