package com.xing.project.movie.ui.activity;

import android.content.res.Resources;
import android.os.Handler;
import android.util.TypedValue;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.xing.project.movie.databinding.ActivityMoviceDataDetailBinding;
import com.xing.project.movie.R;
import com.xing.project.movie.ui.activity.base.BaseActivity;
import com.xing.project.movie.ui.adapter.MovieDataAdapter;
import com.xing.project.movie.util.DataUtils;

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
