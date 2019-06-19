package com.xing.project.movie.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.xing.project.movie.entity.NewMovieItemData;
import com.xing.project.movie.repository.MovieRepository;
import com.xing.project.movie.viewmodel.base.BaseViewModel;
import com.xing.project.movie.data.DataResource;
import com.xing.project.movie.data.db.entity.CityEntity;
import com.xing.project.movie.data.db.entity.MovieEntity;

import java.util.List;

/**
 * <p>
 * Created by woxingxioa on 2018-08-09.
 */
public class MovieViewModel extends BaseViewModel {

    private final MutableLiveData<CityEntity> mCity = new MutableLiveData<>();

    public void setCity(CityEntity city) {
        if (mCity.getValue() == null || mCity.getValue().getId() != city.getId()) {
            mCity.setValue(city);
        }
    }

    public LiveData<DataResource<List<MovieEntity>>> getMovieList(boolean now) {
        return Transformations.switchMap(mCity, input ->
                Transformations.switchMap(getLoadLive(), aBoolean -> {
                            if (now) {
                                return MovieRepository.getInstance().getMovieNowList(input.getId());
                            } else {
                                return MovieRepository.getInstance().getMovieFutureList(input.getId());
                            }
                        }
                )
        );
    }


    public LiveData<DataResource<List<NewMovieItemData>>> getMainPageMovie(int position) {
      return Transformations.switchMap(getLoadLive(), isLoad -> MovieRepository.getInstance().getMainPageMovie(position)) ;

    }
}
