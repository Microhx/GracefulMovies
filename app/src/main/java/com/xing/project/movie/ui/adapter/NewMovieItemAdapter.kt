package com.xing.project.movie.ui.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseViewHolder
import com.iarcuschin.simpleratingbar.SimpleRatingBar
import com.xing.project.movie.R
import com.xing.project.movie.entity.NewMovieItemData
import com.xing.project.movie.ui.activity.adaper.BaseCommonAdapter
import com.xing.project.movie.ui.widget.TagGroup
import com.xing.project.movie.util.MathUtils
import com.xing.project.movie.util.StringUtils

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
class NewMovieItemAdapter : BaseCommonAdapter<NewMovieItemData>(ArrayList(), R.layout.item_movie_new) {

  override fun convert(helper: BaseViewHolder?, item: NewMovieItemData) {
    helper?.apply {

      Glide.with(this.getView<ImageView>(R.id.poster_iv).context)
        .load(item.image)
        .placeholder(R.drawable.pic_place_holder)
        .error(R.drawable.pic_error)
        .into(this.getView(R.id.poster_iv))

      this.setText(R.id.id_tv_name, item.movieName)
        .setText(R.id.grade_tv, item.score + "分")
        .setText(R.id.cast_tv, "主演: " + item.mainActor)
        .setText(R.id.release_date_tv, item.publishTime)

      this.getView<TagGroup>(R.id.type_container).setTagData(StringUtils.parseTag(item.movieType), R.color.color_text_dark, R.color.color_blue)
      this.getView<SimpleRatingBar>(R.id.rating_bar).rating = MathUtils.calculateRate(item.score)

    }

  }


}