package com.xw.project.gracefulmovies.data.api.service;

import com.xw.project.gracefulmovies.data.ao.MovieDetail;
import com.xw.project.gracefulmovies.data.ao.bridge.ModelBridge;
import com.xw.project.gracefulmovies.data.db.entity.MovieEntity;

import com.xw.project.gracefulmovies.entity.NewMovieDetailData;
import com.xw.project.gracefulmovies.entity.NewMovieItemData;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * 电影列表接口
 * <p>
 * Created by woxingxiao on 2018-08-09.
 */
public interface MovieService {

    /**
     * 获取推荐电影
     * @return
     */
    @GET("/api/movie/recommend")
    Observable<ModelBridge<List<NewMovieItemData>>> getRecommendMovieList() ;

    /**
     * 获取热门电影
     * @return
     */
    @GET("/api/movie/hot")
    Observable<ModelBridge<List<NewMovieItemData>>> getHotMovieList();

    @GET("/api/movie/queryMovie")
    Observable<ModelBridge<NewMovieDetailData>> getMovieDetails(@Query("movieId") String movieId);


    @GET("Showtime/LocationMovies.api")
    @Headers("Accept-Encoding:*")
    Observable<ModelBridge<List<MovieEntity>>> movieNowListGet(@Query("locationId") int locationId);

    @GET("Movie/MovieComingNew.api")
    @Headers("Accept-Encoding:*")
    Observable<ModelBridge<List<MovieEntity>>> movieFutureListGet(@Query("locationId") int locationId);

    @GET("movie/detail.api")
    @Headers("Accept-Encoding:*")
    Observable<ModelBridge<MovieDetail>> movieDetailGet(
            @Query("locationId") String locationId,
            @Query("movieId") int movieId
    );
}
