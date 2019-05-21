package com.xw.project.gracefulmovies.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xw.project.gracefulmovies.R
import com.xw.project.gracefulmovies._data.DataUtils
import com.xw.project.gracefulmovies.data.ao.SearchData
import com.xw.project.gracefulmovies.ui.activity.adaper.BaseCommonAdapter
import com.xw.project.gracefulmovies.ui.activity.base.BaseRefreshActivity
import com.xw.project.gracefulmovies.ui.search.adapter.SearchTitleAdapter

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
class MovieSearchActivity : BaseRefreshActivity<SearchData>() {


  override fun subClassInitAdapter(): BaseCommonAdapter<SearchData> {
    val movieSearchAdapter = MovieSearchAdapter()

    movieSearchAdapter.addHeaderView(createHeadView())

    return movieSearchAdapter
  }

  private fun createHeadView(): View {
    val rootView = LayoutInflater.from(applicationContext).inflate(R.layout.movie_search_title, null, false)
    val typeRecycler = rootView.findViewById<RecyclerView>(R.id.id_recyler_type)
    val locationRecycler = rootView.findViewById<RecyclerView>(R.id.id_recyler_location)
    val timeRecycler = rootView.findViewById<RecyclerView>(R.id.id_recyler_time)

    typeRecycler.apply {
      adapter = SearchTitleAdapter(DataUtils.getSearchTitle(12))
      layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    locationRecycler.apply {
      adapter = SearchTitleAdapter(DataUtils.getSearchTitle(28))
      layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    timeRecycler.apply {
      adapter = SearchTitleAdapter(DataUtils.getSearchTitle(16))
      layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    return rootView
  }


}