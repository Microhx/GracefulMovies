package com.xing.project.movie.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.xing.project.movie.repository.BoxOfficeRepository;
import com.xing.project.movie.viewmodel.base.BaseViewModel;
import com.xing.project.movie.data.DataResource;
import com.xing.project.movie.data.db.entity.BoxOfficeEntity;
import com.xing.project.movie.repository.BoxOfficeRepository;
import com.xing.project.movie.viewmodel.base.BaseViewModel;
import com.xing.project.movie.repository.BoxOfficeRepository;
import com.xing.project.movie.viewmodel.base.BaseViewModel;

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
