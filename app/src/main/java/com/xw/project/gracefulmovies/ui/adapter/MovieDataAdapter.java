package com.xw.project.gracefulmovies.ui.adapter;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import com.xw.project.gracefulmovies.R;
import com.xw.project.gracefulmovies.data.db.entity.MovieDataItemEntity;
import com.xw.project.gracefulmovies.data.db.entity.MovieDetailEntity;
import com.xw.project.gracefulmovies.ui.adapter.base.BaseBindingListAdapter;

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
