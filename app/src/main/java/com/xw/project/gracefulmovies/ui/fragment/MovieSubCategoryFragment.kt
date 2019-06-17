package com.xw.project.gracefulmovies.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xw.project.gracefulmovies.entity.BaseSuperData
import com.xw.project.gracefulmovies.entity.NewMovieItemData
import com.xw.project.gracefulmovies.ui.activity.adaper.BaseCommonAdapter
import com.xw.project.gracefulmovies.ui.activity.base.NewBaseRefreshFragment
import com.xw.project.gracefulmovies.ui.search.adapter.MovieDataSearchAdapter
import com.xw.project.gracefulmovies.util.Logy
import com.xw.project.gracefulmovies.viewmodel.MovieCategoryViewModel

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
class MovieSubCategoryFragment : NewBaseRefreshFragment<NewMovieItemData>() {

    private lateinit var mMovieCategoryViewModel : MovieCategoryViewModel
    private lateinit var mMovieType:String

    override fun receiveIntentOrBundles(arguments: Bundle?) {
        mMovieType = arguments!!.getString("movieType")!!
    }

    override fun subClassInitAdapter(): BaseCommonAdapter<NewMovieItemData> {
        return MovieDataSearchAdapter()
    }

    override fun initOnBindLifeCycle() {
        mMovieCategoryViewModel = ViewModelProviders.of(this).get(MovieCategoryViewModel::class.java)
        mMovieCategoryViewModel.itemMovieData.observe(viewLifecycleOwner,
                Observer<BaseSuperData<NewMovieItemData>> {

                    Logy.d("data:$it")
                    onDataComing(it)
        })
    }

    override fun gotoRequest(pageIndex: Int) {
        Logy.d("gotRequest:$pageIndex")

        mMovieCategoryViewModel.loadCategroyMovie(mMovieType,pageIndex)
    }


}