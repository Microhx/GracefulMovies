package com.xing.project.movie.repository;

import androidx.lifecycle.LiveData;
import androidx.annotation.Nullable;

import com.xing.project.movie.GMApplication;
import com.xing.project.movie.data.ApiResponse;
import com.xing.project.movie.data.DataResource;
import com.xing.project.movie.data.NetworkBoundResource;
import com.xing.project.movie.data.api.ApiClient;
import com.xing.project.movie.data.api.service.MovieService;
import com.xing.project.movie.data.db.entity.MovieEntity;
import com.xing.project.movie.entity.NewMovieItemData;
import com.xing.project.movie.rx.RxSchedulers;
import com.xing.project.movie.rx.SimpleConsumer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * <p>
 * Created by woxingxiao on 2018-08-09.
 */
public class MovieRepository {

    private MovieRepository() {
    }


    private static class SingletonHolder {
        private static final MovieRepository sInstance = new MovieRepository();
    }

    public static MovieRepository getInstance() {
        return SingletonHolder.sInstance;
    }


    public LiveData<DataResource<List<NewMovieItemData>>> getMainPageMovie(int position) {
        return new NetworkBoundResource<List<NewMovieItemData>>() {
            @Nullable @Override protected LiveData<ApiResponse<List<NewMovieItemData>>> requestApi() {
                MovieService service = new ApiClient().createApi(MovieService.class);
                ApiResponse<List<NewMovieItemData>> response = new ApiResponse<>();
                if(position == 0) {
                    return response.map(service.getRecommendMovieList());
                }
                return response.map(service.getHotMovieList());
            }

        }.getAsLiveData() ;
    }



    public LiveData<DataResource<List<MovieEntity>>> getMovieNowList(int cityId) {
        return new NetworkBoundResource<List<MovieEntity>>() {
            @Nullable
            @Override
            protected LiveData<List<MovieEntity>> loadFromLocal() {
                return GMApplication.getInstance().getDatabase().movieDao().loadMovieNowList();
            }

            @Nullable
            @Override
            protected LiveData<ApiResponse<List<MovieEntity>>> requestApi() {
                MovieService service = new ApiClient().createApi(MovieService.class);
                ApiResponse<List<MovieEntity>> response = new ApiResponse<>();
                return response.map(service.movieNowListGet(cityId));
            }

            @Override
            protected void saveRemoteResult(List<MovieEntity> data) {
                if (data != null && !data.isEmpty()) {
                    Observable.just(data)
                            .map(rawList -> {
                                List<MovieEntity> newList = new ArrayList<>();
                                for (MovieEntity entity : rawList) {
                                    if (entity.getRating() > 0) {
                                        newList.add(entity);
                                    }
                                }
                                return newList;
                            })
                            .compose(RxSchedulers.applyIO())
                            .subscribe(new SimpleConsumer<List<MovieEntity>>() {
                                @Override
                                public void accept(List<MovieEntity> list) {
                                    for (int i = 0, size = list.size(); i < size; i++) {
                                        list.get(i).setRank(i);
                                        list.get(i).setNow(true);
                                    }
                                    GMApplication.getInstance().getDatabase().movieDao().updateMovieNowList(list);
                                }
                            });
                } else {
                    Observable.just("")
                            .compose(RxSchedulers.applyIO())
                            .subscribe(new SimpleConsumer<String>() {
                                @Override
                                public void accept(String it) {
                                    GMApplication.getInstance().getDatabase().movieDao().deleteNow();
                                }
                            });
                }
            }
        }.getAsLiveData();
    }

    public LiveData<DataResource<List<MovieEntity>>> getMovieFutureList(int cityId) {
        return new NetworkBoundResource<List<MovieEntity>>() {
            @Nullable
            @Override
            protected LiveData<List<MovieEntity>> loadFromLocal() {
                return GMApplication.getInstance().getDatabase().movieDao().loadMovieFutureList();
            }

            @Nullable
            @Override
            protected LiveData<ApiResponse<List<MovieEntity>>> requestApi() {
                MovieService service = new ApiClient().createApi(MovieService.class);
                ApiResponse<List<MovieEntity>> response = new ApiResponse<>();
                return response.map(service.movieFutureListGet(cityId));
            }

            @Override
            protected void saveRemoteResult(List<MovieEntity> data) {
                if (data != null && !data.isEmpty()) {
                    Observable.just(data)
                            .map(ArrayList::new)
                            .compose(RxSchedulers.applyIO())
                            .subscribe(new SimpleConsumer<List<MovieEntity>>() {
                                @Override
                                public void accept(List<MovieEntity> list) {
                                    for (int i = 0, size = list.size(); i < size; i++) {
                                        list.get(i).setRank(i);
                                    }
                                    GMApplication.getInstance().getDatabase().movieDao().updateMovieFutureList(list);
                                }
                            });
                } else {
                    Observable.just("")
                            .compose(RxSchedulers.applyIO())
                            .subscribe(new SimpleConsumer<String>() {
                                @Override
                                public void accept(String it) {
                                    GMApplication.getInstance().getDatabase().movieDao().deleteFuture();
                                }
                            });
                }
            }
        }.getAsLiveData();
    }


}
