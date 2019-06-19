package com.xing.project.movie.ui.fragment

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.xing.project.movie.R
import com.xing.project.movie.data.DataResource
import com.xing.project.movie.entity.NewMovieTypeItem
import com.xing.project.movie.ui.activity.base.NewBaseFragment
import com.xing.project.movie.util.CommonUtils
import com.xing.project.movie.view.HxStateView
import com.xing.project.movie.viewmodel.MovieCategoryViewModel

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019-06-17
 *
 * version : 1.0
 *
 * desc : 电影分类fragment
 */
class MovieCategoryFragment : NewBaseFragment() {

    private lateinit var hxStateView: HxStateView
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager

    override fun getResLayoutId(): Int = R.layout.common_layout

    override fun afterViewCreated(rootView: View) {
        hxStateView = rootView.findViewById(R.id.id_hx_state_view)
        hxStateView.addContentView(R.layout.fragment_categroy_layout)

        mTabLayout = hxStateView.contentView.findViewById(R.id.id_tab_layout)
        mViewPager = hxStateView.contentView.findViewById(R.id.id_view_pager)
    }

    override fun onLazyLoadData() {
        hxStateView.showLoading()

        MovieCategoryViewModel().apply {
            getMovieItemTypeList().
                    observe(viewLifecycleOwner, Observer<DataResource<List<NewMovieTypeItem>>>
                    {
                        when(it.status) {
                            DataResource.Status.SUCCESS -> {
                                hxStateView.showContentLayout()
                                showContentLayout(it.data)
                            }

                            DataResource.Status.ERROR,
                            DataResource.Status.EMPTY -> {
                                hxStateView.showError()
                            }

                            else -> {
                                hxStateView.showError()
                            }
                        }
                    })
            load()
        }
    }


    private fun showContentLayout(data: List<NewMovieTypeItem>?) {
        val adapter = CustomPagerAdapter(childFragmentManager, CommonUtils.getSafeArrayList(data))
        mViewPager.adapter = adapter
        mTabLayout.setupWithViewPager(mViewPager)
    }

    class CustomPagerAdapter(fragmentManager: FragmentManager, var itemList:List<NewMovieTypeItem>) :
            FragmentPagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment {
            return MovieSubCategoryFragment.getInstance(itemList[position].id)
        }

        override fun getCount(): Int {
            return itemList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return itemList[position].typeName
        }

    }

}