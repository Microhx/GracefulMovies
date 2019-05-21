package com.xw.project.gracefulmovies.ui.activity;

import android.content.res.Resources;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import com.xw.project.gracefulmovies.GMApplication;
import com.xw.project.gracefulmovies.R;
import com.xw.project.gracefulmovies.data.db.entity.MovieDataItemEntity;
import com.xw.project.gracefulmovies.databinding.ActivityMoviceDataDetailBinding;
import com.xw.project.gracefulmovies.ui.activity.base.BaseActivity;
import com.xw.project.gracefulmovies.ui.adapter.MovieDataAdapter;
import com.xw.project.gracefulmovies.util.DataUtils;
import java.util.List;

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
public class MovieDataDetailActivity extends BaseActivity<ActivityMoviceDataDetailBinding> implements SwipeRefreshLayout.OnRefreshListener {

  private MovieDataAdapter mMovieDataAdapter;

  @Override protected int contentLayoutRes() {
    return R.layout.activity_movice_data_detail;
  }

  @Override protected void afterSetContentView() {
    initializeToolbar();

    initSwipeRefreshLayout();

    initAdapter();
  }

  private void initSwipeRefreshLayout() {
    mBinding.swipeRefreshLayout.setColorSchemeColors(
        ContextCompat.getColor(this, R.color.colorAccent),
        ContextCompat.getColor(this, R.color.colorPrimary)
    );

    mBinding.swipeRefreshLayout.setProgressViewEndTarget(false, (int) TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, 120, Resources.getSystem().getDisplayMetrics()));

    mBinding.swipeRefreshLayout.setOnRefreshListener(this);

  }

  /**
   * 初始化Toolbar的功能
   */
  protected void initializeToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  private void initAdapter() {
    mMovieDataAdapter = new MovieDataAdapter();
    mBinding.recyclerView.setAdapter(mMovieDataAdapter);
    mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    mBinding.swipeRefreshLayout.setRefreshing(true);

    loadData();
  }


  @Override
  public void onRefresh() {

    loadData();

  }

  private void loadData() {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {

        mMovieDataAdapter.setData(DataUtils.getMovieData());
        mMovieDataAdapter.setData(DataUtils.getMovieData());

        mBinding.swipeRefreshLayout.setRefreshing(false);
      }
    },4000);
  }


}
