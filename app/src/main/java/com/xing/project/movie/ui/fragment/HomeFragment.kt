package com.xing.project.movie.ui.fragment

import android.view.View
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.xing.project.movie.R
import com.xing.project.movie.ui.activity.base.NewBaseFragment
import com.xing.project.movie.ui.adapter.TabPagerAdapter

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
class HomeFragment : NewBaseFragment() {

    override fun getResLayoutId(): Int = R.layout.fragment_home_layout

    override fun afterViewCreated(rootView: View) {
        val viewPager = rootView.findViewById<ViewPager>(R.id.view_pager)
        val radioGroup = rootView.findViewById<RadioGroup>(R.id.radio_group)

        val fragments = arrayOfNulls<Fragment>(2)
        fragments[0] = MovieListFragment.getInstance(0)
        fragments[1] = MovieListFragment.getInstance(1)

        val adapter = TabPagerAdapter(childFragmentManager, fragments)
        adapter.setTabTitles(arrayOf(getString(R.string.has_released), getString(R.string.going_to_release)))
        viewPager.adapter = adapter

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val position = if (checkedId == R.id.now_radio) 0 else 1
            if (viewPager.currentItem != position){
                viewPager.currentItem = position
            }
        }
    }

}