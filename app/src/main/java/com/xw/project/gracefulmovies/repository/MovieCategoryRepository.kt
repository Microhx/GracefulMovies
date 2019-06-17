package com.xw.project.gracefulmovies.repository

import androidx.lifecycle.LiveData
import com.xw.project.gracefulmovies.data.ApiResponse
import com.xw.project.gracefulmovies.data.DataResource
import com.xw.project.gracefulmovies.data.NetworkBoundResource
import com.xw.project.gracefulmovies.data.api.ApiClient
import com.xw.project.gracefulmovies.data.api.service.MovieService
import com.xw.project.gracefulmovies.entity.NewMovieItemData

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
class MovieCategoryRepository {

    fun getMovieCategoryList(movieType:String, currentPage:Int) :
                            LiveData<DataResource<List<NewMovieItemData>>> {

        return object : NetworkBoundResource<List<NewMovieItemData>>() {
            override fun requestApi(): LiveData<ApiResponse<List<NewMovieItemData>>>? {
                val service = ApiClient().createApi(MovieService::class.java)
                val response = ApiResponse<List<NewMovieItemData>>()

                return response.map(service.getMovieByType(movieType, currentPage))
            }
        }.asLiveData
    }

}