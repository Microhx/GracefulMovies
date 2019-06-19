package com.xing.project.movie.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.xing.project.movie.GMApplication;
import com.xing.project.movie.data.db.entity.CityEntity;
import com.xing.project.movie.rx.RxSchedulers;
import com.xing.project.movie.rx.SimpleConsumer;

import io.reactivex.Observable;

/**
 * <p>
 * Created by woxignxiao on 2018-08-12.
 */
public class CityRepository {

    private final MutableLiveData<CityEntity> mCity = new MutableLiveData<>();

    public LiveData<CityEntity> getCity() {
        return mCity;
    }

    public void updateCity(CityEntity city) {
        mCity.postValue(city);

        Observable.just("")
                .compose(RxSchedulers.applyIO())
                .subscribe(new SimpleConsumer<String>() {
                    @Override
                    public void accept(String it) {
                        GMApplication.getInstance().getDatabase().cityDao().updateCity(city);
                    }
                });
    }

    public CityEntity getCityEntity() {
        if (mCity.getValue() != null) {
            return mCity.getValue();
        }
        return null;
    }

    public CityEntity genDefaultCity() {
        CityEntity city = new CityEntity();
        city.setId(880);
        city.setName("成都");
        return city;
    }
}
