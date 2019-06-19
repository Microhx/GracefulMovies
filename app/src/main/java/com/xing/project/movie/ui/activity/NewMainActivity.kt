package com.xing.project.movie.ui.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.xing.project.movie.R
import com.xing.project.movie.ui.activity.base.NewBaseActivity
import com.xing.project.movie.ui.fragment.HomeFragment
import com.xing.project.movie.ui.fragment.MineFragment
import com.xing.project.movie.ui.fragment.MovieCategoryFragment
import com.xing.project.movie.ui.fragment.TrendFragment
import com.xing.project.movie.view.BaseRadioLayout
import kotlinx.android.synthetic.main.activity_new_main.*
import java.lang.Exception

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019-06-17
 *
 * version :
 *
 * desc :
 */
class NewMainActivity : NewBaseActivity(), BaseRadioLayout.onRadioButtonClickListener {

    override fun getContentLayout(): Int = R.layout.activity_new_main

    override fun initDatas() {
        id_view_pager.adapter = MainPagerAdapter(supportFragmentManager)
        id_view_pager.offscreenPageLimit = 3

        radio_layout.setRadioButtonClickListener(this)
    }

    override fun onRadioButtonClick(index: Int) {
        id_view_pager.setCurrentItem(index,false)
    }

    class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> {
                    return HomeFragment()
                }

                1 -> {
                    return MovieCategoryFragment()
                }

                2 -> {
                    return TrendFragment()
                }

                3 -> {
                    return MineFragment()
                }

                else -> {
                    throw Exception("the current index is wrong: $position")
                }
            }
        }

        override fun getCount(): Int = 4
    }

}