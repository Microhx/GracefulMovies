package com.xw.project.gracefulmovies.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.xw.project.gracefulmovies.entity.BaseSuperData
import com.xw.project.gracefulmovies.entity.NewMovieItemData
import com.xw.project.gracefulmovies.repository.MovieCategoryRepository
import com.xw.project.gracefulmovies.util.CommonUtils
import com.xw.project.gracefulmovies.viewmodel.base.BaseViewModel

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
class MovieCategoryViewModel : BaseViewModel() {

    val itemMovieData = MutableLiveData<BaseSuperData<NewMovieItemData>>()

    private var mMovieCategoryRepository = MovieCategoryRepository()

    /**
     * 加载分类电影
     */
    fun loadCategroyMovie(movieType:String, currentPage:Int) {
        val categoryList = mMovieCategoryRepository.getMovieCategoryList(movieType, currentPage)
        categoryList.observeForever {

            val superData = BaseSuperData<NewMovieItemData>()

            superData.data = it.data
            superData.index = currentPage

            itemMovieData.postValue(superData)
        }
    }

}