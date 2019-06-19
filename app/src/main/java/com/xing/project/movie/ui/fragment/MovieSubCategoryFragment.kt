package com.xing.project.movie.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xing.project.movie.entity.BaseSuperData
import com.xing.project.movie.entity.NewMovieItemData
import com.xing.project.movie.ui.activity.adaper.BaseCommonAdapter
import com.xing.project.movie.ui.activity.base.NewBaseRefreshFragment
import com.xing.project.movie.ui.search.adapter.MovieDataSearchAdapter
import com.xing.project.movie.util.Logy
import com.xing.project.movie.viewmodel.MovieSubCategoryViewModel

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

    private lateinit var mMovieCategoryViewModel : MovieSubCategoryViewModel
    private lateinit var mMovieType:String

    override fun isNotViewPagerItem(): Boolean = false

    override fun receiveIntentOrBundles(arguments: Bundle?) {
        mMovieType = arguments!!.getString("movieType")!!
    }

    override fun subClassInitAdapter(): BaseCommonAdapter<NewMovieItemData> {
        return MovieDataSearchAdapter()
    }

    override fun initOnBindLifeCycle() {
        mMovieCategoryViewModel = ViewModelProviders.of(this).get(MovieSubCategoryViewModel::class.java)
        mMovieCategoryViewModel.itemMovieData.observe(viewLifecycleOwner,
                Observer<BaseSuperData<NewMovieItemData>> {

                    Logy.d("data:$it")
                    onDataComing(it)
        })
    }

    override fun gotoRequest(pageIndex: Int) {
        Logy.d("gotRequest:$pageIndex")

        mMovieCategoryViewModel.loadCategoryMovie(mMovieType,pageIndex)
    }

    companion object {
        fun getInstance(movieType:Int) : MovieSubCategoryFragment {
            val bundle = Bundle()
            bundle.putString("movieType",movieType.toString())

            val fragment = MovieSubCategoryFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


}
