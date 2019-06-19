package com.xing.project.movie.viewmodel

import androidx.lifecycle.MutableLiveData
import com.xing.project.movie.entity.BaseSuperData
import com.xing.project.movie.entity.NewMovieItemData
import com.xing.project.movie.repository.MovieCategoryRepository
import com.xing.project.movie.viewmodel.base.BaseViewModel

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
class MovieSubCategoryViewModel : BaseViewModel() {

    val itemMovieData = MutableLiveData<BaseSuperData<NewMovieItemData>>()

    private var mMovieCategoryRepository = MovieCategoryRepository()

    /**
     * 加载分类电影
     */
    fun loadCategoryMovie(movieType:String, currentPage:Int) {
        val categoryList = mMovieCategoryRepository.getMovieCategoryList(movieType, currentPage)
        categoryList.observeForever {

            val superData = BaseSuperData<NewMovieItemData>()

            superData.data = it.data
            superData.index = currentPage

            itemMovieData.postValue(superData)
        }
    }

}