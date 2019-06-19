package com.xing.project.movie.repository

import androidx.lifecycle.LiveData
import com.xing.project.movie.data.ApiResponse
import com.xing.project.movie.data.DataResource
import com.xing.project.movie.data.NetworkBoundResource
import com.xing.project.movie.data.api.ApiClient
import com.xing.project.movie.data.api.service.MovieService
import com.xing.project.movie.entity.NewMovieTypeItem

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
class NewMovieTypeRepository {

    fun getMovieTypeList() : LiveData<DataResource<List<NewMovieTypeItem>>> {
       return object : NetworkBoundResource<List<NewMovieTypeItem>>() {
           override fun requestApi(): LiveData<ApiResponse<List<NewMovieTypeItem>>>? {

               val service = ApiClient().createApi(MovieService::class.java)
               val response = ApiResponse<List<NewMovieTypeItem>>()
               return response.map(service.movieTypeItemList)
           }
       }.asLiveData

    }
}