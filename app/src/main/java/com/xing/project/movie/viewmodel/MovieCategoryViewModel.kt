package com.xing.project.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.xing.project.movie.data.DataResource
import com.xing.project.movie.entity.NewMovieTypeItem
import com.xing.project.movie.repository.NewMovieTypeRepository
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
class MovieCategoryViewModel  : BaseViewModel() {

    // val movieItemTypeList = MutableLiveData<List<NewMovieTypeItem>>()
    // NewMovieTypeRepository().getMovieTypeList()

    fun getMovieItemTypeList() : LiveData<DataResource<List<NewMovieTypeItem>>> {
        return Transformations.switchMap<Boolean,
                DataResource<List<NewMovieTypeItem>>>(loadLive)
        { NewMovieTypeRepository().getMovieTypeList() }
    }


}