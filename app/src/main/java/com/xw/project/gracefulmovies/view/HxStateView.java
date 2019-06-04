package com.xw.project.gracefulmovies.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import androidx.annotation.IntDef;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xw.project.gracefulmovies.R;
import com.xw.project.gracefulmovies.util.Logy;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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
 */
public class HxStateView extends FrameLayout {

  public static final int LOADING = 0;
  public static final int NO_DATA = 1;
  public static final int ERROR = 2;
  public static final int SUCCESS = 3;

  @IntDef({LOADING, NO_DATA, ERROR, SUCCESS})
  @Retention(RetentionPolicy.SOURCE)
  @interface Status {}

  private View mEmptyView;
  private View mLoadingView;
  private View mContentView;
  private View mErrorView;


  public HxStateView(@NonNull Context context) {
    this(context,null);
  }

  public HxStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs,0);
  }

  public HxStateView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initViews();
  }

  private void initViews() {
    View.inflate(getContext(), R.layout.hx_state_view_layout,this);
  }

  public void checkEmptyView() {
    if(null == mEmptyView) {
      ViewStub viewStub = findViewById(R.id.id_view_stub_empty);
      mEmptyView = viewStub.inflate();
    }
  }

  private void checkLoadingView() {
    if(null == mLoadingView) {
      ViewStub viewStub = findViewById(R.id.id_view_stub_loading);
      mLoadingView = viewStub.inflate();
    }
  }

  private void checkErrorView(){
    if(null == mErrorView) {
      ViewStub viewStub = findViewById(R.id.id_view_stub_error);
      mErrorView = viewStub.inflate();
    }
  }

  public View getContentView() {
    assert  mContentView != null ;
    return mContentView;
  }


  public void addContentView(@LayoutRes int layoutId) {
    mContentView = View.inflate(getContext(), layoutId,null);
    addView(mContentView, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
  }

  public void showEmpty() {
    hideAllViews(mLoadingView, mErrorView, mContentView);
    checkEmptyView();
    mEmptyView.setVisibility(VISIBLE);
  }

  public void showError() {
    hideAllViews(mEmptyView, mLoadingView, mContentView);
    checkErrorView();
    mErrorView.setVisibility(VISIBLE);
  }

  public void showLoading(){
    hideAllViews(mEmptyView, mErrorView, mContentView);
    checkLoadingView();
    mLoadingView.setVisibility(VISIBLE);
  }

  public void showContentLayout() {
    hideAllViews(mEmptyView, mLoadingView, mErrorView);

    if(null != mContentView && mContentView.getVisibility() != VISIBLE) {
      mContentView.setVisibility(VISIBLE);
    }
  }

  private void hideAllViews(View... views) {
    for (View v : views){
      if(null != v && v.getVisibility() != GONE) {
        v.setVisibility(GONE);
      }
    }
  }

}
