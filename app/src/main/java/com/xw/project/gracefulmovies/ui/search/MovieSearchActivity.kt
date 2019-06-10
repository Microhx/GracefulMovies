package com.xw.project.gracefulmovies.ui.search

import android.text.Editable
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.xw.project.gracefulmovies.R
import com.xw.project.gracefulmovies.data.ao.SearchMovieData
import com.xw.project.gracefulmovies.entity.NewMovieItemData
import com.xw.project.gracefulmovies.ui.activity.adaper.BaseCommonAdapter
import com.xw.project.gracefulmovies.ui.activity.base.BaseRefreshActivity
import com.xw.project.gracefulmovies.ui.search.adapter.MovieDataSearchAdapter
import com.xw.project.gracefulmovies.util.CommonUtils
import com.xw.project.gracefulmovies.util.Logy
import com.xw.project.gracefulmovies.view.CustomSearchView
import com.xw.project.gracefulmovies.viewmodel.MovieSearchViewModel

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/5/21
 *
 * version : 1.0.1
 *
 * desc : 电影文字搜索
 *
 *
 */
class MovieSearchActivity : BaseRefreshActivity<NewMovieItemData>(), CustomSearchView.SearchViewInterface {

  private lateinit var mMovieSearchViewModel: MovieSearchViewModel
  private val mMovieDataSearchAdapter = MovieDataSearchAdapter()

  private var mCurrentPage = START_PAGE;
  private var mCurrentKeyWords = ""

  override fun initializeToolbar() {
    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    val customSearchView = CustomSearchView(applicationContext)
    customSearchView.searchViewInterface = this
    toolbar.addView(customSearchView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
  }


  override fun initLoadData() {
    mMovieSearchViewModel = ViewModelProviders.of(this).get(MovieSearchViewModel::class.java)
    mMovieSearchViewModel.itemData.observe(this, Observer {
        Logy.i("initData:$it")

        if(it.index == START_PAGE) {
          mMovieDataSearchAdapter.replaceData(it.data)

        }else{
          mCurrentPage ++
          mMovieDataSearchAdapter.addData(it.data)
        }

        mMovieDataSearchAdapter.apply {
          setEnableLoadMore(CommonUtils.checkCollectionSize(it.data, PAGE_SIZE))
        }
    })
  }


  override fun subClassInitAdapter(): BaseCommonAdapter<NewMovieItemData> {
    return mMovieDataSearchAdapter
  }


  override fun onCancel() {
    finish()
  }

  override fun gotoSearch(s: Editable?) {
    this.mCurrentKeyWords = s.toString()
    mCurrentPage = START_PAGE
    mMovieSearchViewModel.searchMovieInfo(this.mCurrentKeyWords, mCurrentPage)
  }

  override fun loadMoreData() {
    mMovieSearchViewModel.searchMovieInfo(this.mCurrentKeyWords, mCurrentPage)
  }


  override fun onRefresh() {
    mCurrentPage = START_PAGE
    mMovieSearchViewModel.searchMovieInfo(this.mCurrentKeyWords, mCurrentPage)
  }

}


//    setSupportActionBar(toolbar)
//    if (supportActionBar != null) {
//      supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//    }