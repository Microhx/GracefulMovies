package com.xw.project.gracefulmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.xw.project.gracefulmovies.data.DataResource
import com.xw.project.gracefulmovies.data.ao.MovieDetail
import com.xw.project.gracefulmovies.entity.NewMovieTypeItem
import com.xw.project.gracefulmovies.repository.MovieCategoryRepository
import com.xw.project.gracefulmovies.repository.MovieDetailRepository
import com.xw.project.gracefulmovies.repository.NewMovieTypeRepository
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
class MovieCategoryViewModel  : BaseViewModel() {

    // val movieItemTypeList = MutableLiveData<List<NewMovieTypeItem>>()
    // NewMovieTypeRepository().getMovieTypeList()

    fun getMovieItemTypeList() : LiveData<DataResource<List<NewMovieTypeItem>>> {
        return Transformations.switchMap<Boolean,
                DataResource<List<NewMovieTypeItem>>>(loadLive)
        { NewMovieTypeRepository().getMovieTypeList() }
    }


}