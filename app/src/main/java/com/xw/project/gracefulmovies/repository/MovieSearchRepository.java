package com.xw.project.gracefulmovies.repository;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import com.xw.project.gracefulmovies.data.ApiResponse;
import com.xw.project.gracefulmovies.data.DataResource;
import com.xw.project.gracefulmovies.data.NetworkBoundResource;
import com.xw.project.gracefulmovies.data.ao.MovieDetail;
import com.xw.project.gracefulmovies.data.api.ApiClient;
import com.xw.project.gracefulmovies.data.api.service.MovieService;
import com.xw.project.gracefulmovies.entity.NewMovieItemData;
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

  public LiveData<DataResource<List<NewMovieItemData>>> searchMovieData(String movieName,int pageIndex) {

    return new NetworkBoundResource<List<NewMovieItemData>>() {
      @Nullable @Override protected LiveData<ApiResponse<List<NewMovieItemData>>> requestApi() {
        MovieService service = new ApiClient().createApi(MovieService.class);
        ApiResponse<List<NewMovieItemData>> response = new ApiResponse<>();
        return response.map(service.searchMovieData(movieName, pageIndex));

      }
    }.getAsLiveData();


  }
}
