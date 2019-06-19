package com.xing.project.movie.ui.activity.base

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.xing.project.movie.R
import com.xing.project.movie.entity.BaseSuperData
import com.xing.project.movie.ui.activity.adaper.BaseCommonAdapter
import com.xing.project.movie.util.CommonUtils
import com.xing.project.movie.util.Logy
import com.xing.project.movie.view.CustomLoadMoreView
import com.xing.project.movie.view.HxStateView

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019-06-17
 *
 * version :
 *
 * desc : 新的上下拉刷新数据Fragment
 */
abstract class NewBaseRefreshFragment<T>: NewBaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var hxStateView: HxStateView
    private lateinit var mRecyclerView:RecyclerView
    private lateinit var mRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerViewAdapter : BaseCommonAdapter<T>

    private var mCurrentPage = FIRST_PAGE

    override fun getResLayoutId(): Int {
        return R.layout.common_layout
    }

    override fun afterViewCreated(rootView: View) {
        hxStateView = rootView.findViewById(R.id.id_hx_state_view)
        hxStateView.addContentView(R.layout.comment_refresh_layout)

        mRefreshLayout = hxStateView.contentView.findViewById(R.id.swipe_refresh_layout)
        mRecyclerView = hxStateView.contentView.findViewById(R.id.recycler_view)

        initRefreshLayout()

        initRecyclerView()

        initOnBindLifeCycle()
    }

    private fun initRefreshLayout() {
        mRefreshLayout.apply {
            setColorSchemeColors(
                    ContextCompat.getColor(context, R.color.colorAccent),
                    ContextCompat.getColor(context, R.color.colorPrimary)
            )

            setProgressViewEndTarget(
                    false, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120f, Resources.getSystem().displayMetrics).toInt()
            )

            if(refreshLayoutEnabled()) {
                setOnRefreshListener(this@NewBaseRefreshFragment)
            }
        }
    }

    private fun initRecyclerView() {
        mRecyclerView.apply {
            initLayoutManager(this)
            initAdapter(this)
            initItemDo(this)
        }
    }

    open fun initLayoutManager(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


    open fun initAdapter(recyclerView: RecyclerView) {
        recyclerViewAdapter = subClassInitAdapter()
        recyclerViewAdapter.apply {
            setOnItemClickListener{_,_,position ->
                run {
                    onItemChildClick(position)
                }
            }

            //load more view
            setLoadMoreView(CustomLoadMoreView())

            setOnLoadMoreListener({
                Logy.i("onLoadMore:" + System.currentTimeMillis())

                loadMoreData()
            }, recyclerView)
        }

        recyclerView.adapter = recyclerViewAdapter
    }


    open fun initItemDo(recyclerView: RecyclerView) {
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }


    abstract fun subClassInitAdapter(): BaseCommonAdapter<T>

    open fun refreshLayoutEnabled() = true

    override fun onRefresh() {
        mCurrentPage = FIRST_PAGE
        gotoRequest(mCurrentPage)
    }

    open fun loadMoreData() {
        gotoRequest(mCurrentPage)
    }

    open fun onItemChildClick(position: Int) {}

    /**
     * 数据处理
     */
    protected fun onDataComing(target: BaseSuperData<T>) {
        mRefreshLayout.isRefreshing = false

        val arrayList = CommonUtils.getSafeArrayList(target.data)
        if(target.index <= FIRST_PAGE) {
            recyclerViewAdapter.replaceData(arrayList)
        }else {
            recyclerViewAdapter.addData(arrayList)
        }

        mCurrentPage ++

        if(CommonUtils.checkCollection(recyclerViewAdapter.data)){
            hxStateView.showContentLayout()

            recyclerViewAdapter.apply {
                //load finished
                loadMoreComplete()

                if(CommonUtils.checkCollectionSize(arrayList, PAGE_SIZE)) {
                    setEnableLoadMore(true)
                }else{
                    loadMoreEnd()
                }
            }

        }else{
            hxStateView.showEmpty()
        }
    }

    protected fun onErrorComing(target: BaseSuperData<T>) {
        mRefreshLayout.isRefreshing = false

        if(target.index <= FIRST_PAGE) {
            hxStateView.showError()
        }else {
            //TODO
            Logy.d("onErrorComing : ${target.index}")
        }
    }

    open fun initOnBindLifeCycle() {}

    override fun onLazyLoadData() {
        onRefresh()
    }

    open fun gotoRequest(pageIndex:Int) {}

    companion object {
        const val FIRST_PAGE = 1
        const val PAGE_SIZE = 20
    }
}