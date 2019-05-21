package com.xw.project.gracefulmovies.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.xw.project.gracefulmovies.data.DataResource;
import com.xw.project.gracefulmovies.data.db.entity.BoxOfficeEntity;
import com.xw.project.gracefulmovies.repository.BoxOfficeRepository;
import com.xw.project.gracefulmovies.viewmodel.base.BaseViewModel;

import java.util.List;

/**
 * <p>
 * Created by woxingxiao on 2018-08-17.
 */
public class BoxOfficeViewModel extends BaseViewModel {

    public LiveData<DataResource<List<BoxOfficeEntity>>> getBoxOffices() {
        return Transformations.switchMap(getLoadLive(), input -> new BoxOfficeRepository().getBoxOffices());
    }
}
