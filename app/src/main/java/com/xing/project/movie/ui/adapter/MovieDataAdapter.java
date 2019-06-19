package com.xing.project.movie.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.xing.project.movie.R;
import com.xing.project.movie.data.db.entity.MovieDataItemEntity;
import com.xing.project.movie.ui.adapter.base.BaseBindingListAdapter;

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
public class MovieDataAdapter extends BaseBindingListAdapter<MovieDataItemEntity> {

  public MovieDataAdapter() {
    super(new DiffUtil.ItemCallback<MovieDataItemEntity>() {
      @Override public boolean areItemsTheSame(@NonNull MovieDataItemEntity movieDetailEntity, @NonNull MovieDataItemEntity t1) {
        return movieDetailEntity.name.equals(t1.name);
      }

      @Override public boolean areContentsTheSame(@NonNull MovieDataItemEntity movieDetailEntity, @NonNull MovieDataItemEntity t1) {
        return movieDetailEntity.name.equals(t1.name);
      }
    });
  }

  @Override protected int itemLayoutRes() {
    return R.layout.item_movie_data;
  }


}
