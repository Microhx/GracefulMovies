package com.xw.project.gracefulmovies.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.xw.project.gracefulmovies.data.DataResource;
import com.xw.project.gracefulmovies.data.ao.MovieDetail;
import com.xw.project.gracefulmovies.entity.NewMovieDetailData;
import com.xw.project.gracefulmovies.repository.MovieDetailRepository;
import com.xw.project.gracefulmovies.ui.fragment.MovieListFragment;
import com.xw.project.gracefulmovies.viewmodel.base.BaseViewModel;

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