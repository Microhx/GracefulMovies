package com.xing.project.movie.repository;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.xing.project.movie.data.ApiResponse;
import com.xing.project.movie.data.DataResource;
import com.xing.project.movie.data.NetworkBoundResource;
import com.xing.project.movie.data.api.ApiClient;
import com.xing.project.movie.data.api.service.MovieService;
import com.xing.project.movie.entity.NewMovieItemData;

import java.util.List;

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/6/10
 *
 * version : 1.0.1
 *
 * desc :
 */
public class MovieSearchRepository {

  public LiveData<DataResource<List<NewMovieItemData>>> searchMovieData(String movieName, int pageIndex) {

    return new NetworkBoundResource<List<NewMovieItemData>>() {
      @Nullable @Override protected LiveData<ApiResponse<List<NewMovieItemData>>> requestApi() {
        MovieService service = new ApiClient().createApi(MovieService.class);
        ApiResponse<List<NewMovieItemData>> response = new ApiResponse<>();
        return response.map(service.searchMovieData(movieName, pageIndex));

      }
    }.getAsLiveData();


  }
}
