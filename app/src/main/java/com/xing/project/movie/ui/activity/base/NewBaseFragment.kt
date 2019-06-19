package com.xing.project.movie.ui.activity.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019-06-17
 *
 * version :
 *
 * desc : 新的Fragment基础类
 */
abstract class NewBaseFragment : Fragment() {

    private var isVisibleToUser = isNotViewPagerItem()

    private var isViewPrepared = false

    private var isDataHasLoaded = false

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        receiveIntentOrBundles(arguments)
    }

    open fun receiveIntentOrBundles(arguments: Bundle?) {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(getResLayoutId(), container, false)
        afterViewCreated(rootView)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepared = true
        lazyLoad()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser

        lazyLoad()
    }

    protected abstract fun getResLayoutId() : Int

    open fun afterViewCreated(rootView:View) {}

    private fun lazyLoad() {
        if(!isDataHasLoaded && isViewPrepared && isVisibleToUser) {
            isDataHasLoaded = true
            onLazyLoadData()
        }
    }

    open fun onLazyLoadData() {}

    open fun isNotViewPagerItem(): Boolean = true

    override fun onDestroy() {
        isDataHasLoaded = false
        isViewPrepared = false

        super.onDestroy()
    }

    override fun onDestroyView() {
        isDataHasLoaded = false
        isViewPrepared = false

        super.onDestroyView()
    }
}