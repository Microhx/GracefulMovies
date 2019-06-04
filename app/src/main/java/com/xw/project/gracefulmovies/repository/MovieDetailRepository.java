package com.xw.project.gracefulmovies.repository;

import androidx.lifecycle.LiveData;
import androidx.annotation.Nullable;

import com.xw.project.gracefulmovies.data.ApiResponse;
import com.xw.project.gracefulmovies.data.DataResource;
import com.xw.project.gracefulmovies.data.NetworkBoundResource;
import com.xw.project.gracefulmovies.data.ao.MovieDetail;
import com.xw.project.gracefulmovies.data.api.ApiClient;
import com.xw.project.gracefulmovies.data.api.service.MovieService;
import com.xw.project.gracefulmovies.entity.NewMovieDetailData;

/**
 * <p>
 * Created by woxingxiao on 2018-08-15.
 */
public class MovieDetailRepository {

    public LiveData<DataResource<MovieDetail>> getMovieDetails(String locationId, int movieId) {
        return new NetworkBoundResource<MovieDetail>() {

            @Nullable
            @Override
            protected LiveData<ApiResponse<MovieDetail>> requestApi() {
                MovieService service = new ApiClient().createApi(MovieService.class);
                ApiResponse<MovieDetail> response = new ApiResponse<>();
                return response.map(service.movieDetailGet(locationId, movieId));
            }
        }.getAsLiveData();
    }

    public LiveData<DataResource<NewMovieDetailData>> getNewMovieDetails(String movieId) {
        return new NetworkBoundResource<NewMovieDetailData>(){
            @Nullable @Override protected LiveData<ApiResponse<NewMovieDetailData>> requestApi() {
                MovieService service = new ApiClient().createApi(MovieService.class);
                ApiResponse<NewMovieDetailData> response = new ApiResponse<>();
                return response.map(service.getMovieDetails( movieId));
            }

        }.getAsLiveData();
    }

}
