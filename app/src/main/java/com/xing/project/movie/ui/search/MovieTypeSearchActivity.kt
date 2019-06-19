package com.xing.project.movie.ui.search

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xing.project.movie.R
import com.xing.project.movie._data.DataUtils
import com.xing.project.movie.data.ao.SearchData
import com.xing.project.movie.ui.activity.adaper.BaseCommonAdapter
import com.xing.project.movie.ui.activity.base.BaseRefreshActivity
import com.xing.project.movie.ui.search.adapter.MovieTypeSearchAdapter
import com.xing.project.movie.ui.search.adapter.SearchTitleAdapter

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/5/21
 *
 * version : 1.0.1
 *
 * desc : 电影类型搜索
 *
 */
class MovieTypeSearchActivity : BaseRefreshActivity<SearchData>() {


  override fun subClassInitAdapter(): BaseCommonAdapter<SearchData> {
    val movieSearchAdapter = MovieTypeSearchAdapter()

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


  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.search_menu,menu)
    return true
  }


  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    if(item!!.itemId == R.id.action_search) {
      navigate(MovieSearchActivity::class.java)
      return true
    }

    return super.onOptionsItemSelected(item)
  }


}