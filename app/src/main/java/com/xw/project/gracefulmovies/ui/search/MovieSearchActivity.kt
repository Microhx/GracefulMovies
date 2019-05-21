package com.xw.project.gracefulmovies.ui.search

import android.text.Editable
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.xw.project.gracefulmovies.R
import com.xw.project.gracefulmovies.data.ao.SearchMovieData
import com.xw.project.gracefulmovies.ui.activity.adaper.BaseCommonAdapter
import com.xw.project.gracefulmovies.ui.activity.base.BaseRefreshActivity
import com.xw.project.gracefulmovies.ui.search.adapter.MovieDataSearchAdapter
import com.xw.project.gracefulmovies.view.CustomSearchView

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
class MovieSearchActivity : BaseRefreshActivity<SearchMovieData>(), CustomSearchView.SearchViewInterface {

  override fun initializeToolbar() {
    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    val customSearchView = CustomSearchView(applicationContext)
    customSearchView.searchViewInterface = this

    toolbar.addView(customSearchView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))

//    setSupportActionBar(toolbar)
//    if (supportActionBar != null) {
//      supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//    }
  }


  override fun subClassInitAdapter(): BaseCommonAdapter<SearchMovieData> {
    return MovieDataSearchAdapter()
  }


  override fun onCancel() {
    finish()
  }

  override fun gotoSearch(s: Editable?) {
    //TODO goto Search

  }



}