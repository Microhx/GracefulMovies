package com.xing.project.movie.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.xing.project.movie.GMApplication;
import com.xing.project.movie.data.db.entity.CityEntity;

/**
 * <p>
 * Created by woxingxiao on 2018-08-12.
 */
public class CityViewModel extends ViewModel {

    private final LiveData<CityEntity> mCity;

    public CityViewModel() {
        mCity = GMApplication.getInstance().getCityRepository().getCity();
    }

    public LiveData<CityEntity> getCity() {
        return mCity;
    }
}
