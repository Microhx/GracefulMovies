package com.xw.project.gracefulmovies.ui.activity.base

import android.content.res.Resources
import android.util.TypedValue
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.xw.project.gracefulmovies.R
import com.xw.project.gracefulmovies.databinding.ActivityRefreshLayoutBinding
import com.xw.project.gracefulmovies.ui.activity.adaper.BaseCommonAdapter
import com.xw.project.gracefulmovies.view.CustomLoadMoreView

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
abstract class BaseRefreshActivity<T> : BaseActivity<ActivityRefreshLayoutBinding>(), SwipeRefreshLayout.OnRefreshListener {

  private lateinit var recyclerViewAdapter : BaseCommonAdapter<T>

  override fun contentLayoutRes(): Int {
    return R.layout.activity_refresh_layout
  }


  override fun afterSetContentView() {

    initializeToolbar()

    initRefreshLayout()

    initRecyclerView()

    initLoadData()
  }

  open fun initLoadData() {}

  /**
   * 初始化上下拉加载组件
   */
  private fun initRefreshLayout() {
    mBinding.swipeRefreshLayout.apply {
      setColorSchemeColors(
        ContextCompat.getColor(context, R.color.colorAccent),
        ContextCompat.getColor(context, R.color.colorPrimary)
      )

      setProgressViewEndTarget(
        false, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120f, Resources.getSystem().displayMetrics).toInt()
      )

      if(refreshLayoutEnabled()) {
          setOnRefreshListener(this@BaseRefreshActivity)
      }
    }
  }

  private fun initRecyclerView() {
    mBinding.recyclerView.apply {
      initLayoutManager(this)
      initAdapter(this)
      initItemDo(this)
    }

  }

  open fun initItemDo(recyclerView: RecyclerView) {
    recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
  }


  open fun initAdapter(recyclerView: RecyclerView) {
    recyclerViewAdapter = subClassInitAdapter()
    recyclerViewAdapter.apply {
        setOnItemChildClickListener{_,_,position -> {
            //TODO
          }
        }

        //load more view
        setLoadMoreView(CustomLoadMoreView())

        setOnLoadMoreListener({
          loadMoreData()
        }, mBinding.recyclerView)
    }

    mBinding.recyclerView.adapter = recyclerViewAdapter
  }



  open fun initLayoutManager(recyclerView: RecyclerView) {
    recyclerView.layoutManager = LinearLayoutManager(applicationContext)
  }

  open fun refreshLayoutEnabled() = true

  override fun onRefresh() {}

  open fun loadMoreData() {}

  abstract fun subClassInitAdapter(): BaseCommonAdapter<T>


}