package com.xing.project.movie.repository;

import androidx.lifecycle.LiveData;
import androidx.annotation.Nullable;

import com.xing.project.movie.GMApplication;
import com.xing.project.movie.data.ApiResponse;
import com.xing.project.movie.data.DataResource;
import com.xing.project.movie.data.NetworkBoundResource;
import com.xing.project.movie.data.api.ApiClient;
import com.xing.project.movie.data.api.service.BoxOfficeService;
import com.xing.project.movie.data.db.entity.BoxOfficeEntity;
import com.xing.project.movie.rx.RxSchedulers;
import com.xing.project.movie.rx.SimpleConsumer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * <p>
 * Created by woxingxiao on 2018-08-17.
 */
public class BoxOfficeRepository {

    public LiveData<DataResource<List<BoxOfficeEntity>>> getBoxOffices() {
        return new NetworkBoundResource<List<BoxOfficeEntity>>() {
            @Override
            protected boolean ifFetchNetworkFailedThenLoadLocalData() {
                return true;
            }

            @Nullable
            @Override
            protected LiveData<List<BoxOfficeEntity>> loadFromLocal() {
                return GMApplication.getInstance().getDatabase().boxOfficeDao().loadBoxOfficeList();
            }

            @Nullable
            @Override
            protected LiveData<ApiResponse<List<BoxOfficeEntity>>> requestApi() {
                BoxOfficeService service = new ApiClient().createApi("http://api.shenjian.io/", BoxOfficeService.class);
                ApiResponse<List<BoxOfficeEntity>> response = new ApiResponse<>();
                return response.map(service.fetchBoxOfficeGet());
            }

            @Override
            protected void saveRemoteResult(List<BoxOfficeEntity> data) {
                if (data != null && !data.isEmpty()) {
                    Observable.just(data)
                            .map(ArrayList::new)
                            .compose(RxSchedulers.applyIO())
                            .subscribe(new SimpleConsumer<List<BoxOfficeEntity>>() {
                                @Override
                                public void accept(List<BoxOfficeEntity> list) {
                                    GMApplication.getInstance().getDatabase().boxOfficeDao().update(list);
                                }
                            });
                } else {
                    Observable.just("")
                            .compose(RxSchedulers.applyIO())
                            .subscribe(new SimpleConsumer<String>() {
                                @Override
                                public void accept(String it) {
                                    GMApplication.getInstance().getDatabase().boxOfficeDao().delete();
                                }
                            });
                }
            }
        }.getAsLiveData();
    }
}
