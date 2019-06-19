package com.xing.project.movie.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.xing.project.movie.entity.NewMovieDetailData;
import com.xing.project.movie.repository.MovieDetailRepository;
import com.xing.project.movie.viewmodel.base.BaseViewModel;
import com.xing.project.movie.data.DataResource;
import com.xing.project.movie.data.ao.MovieDetail;

/**
 * <p>
 * Created by woxingxiao on 2018-08-15.
 */
public class MovieDetailViewModel extends BaseViewModel {

    public LiveData<DataResource<MovieDetail>> getMovieDetails(String locationId, int movieId) {
        return Transformations.switchMap(getLoadLive(), input ->
                new MovieDetailRepository().getMovieDetails(locationId, movieId)
        );
    }

    public LiveData<DataResource<NewMovieDetailData>> getNewMovieDetails(String movieId) {
        return Transformations.switchMap(getLoadLive(), input ->
            new MovieDetailRepository().getNewMovieDetails(movieId)) ;
    }



}