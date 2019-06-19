package com.xing.project.movie.repository;

import com.xing.project.movie.data.ao.ReGeoResult;
import com.xing.project.movie.data.api.ApiClient;
import com.xing.project.movie.data.api.ApiException;
import com.xing.project.movie.data.api.service.ReGeoService;

import io.reactivex.Observable;

import static com.xing.project.movie.data.api.ApiException.CODE_FAILED;

/**
 * <p>
 * Created by woxingxiao on 2018-08-18.
 */
public class LocationRepository {

    private ReGeoService mService;

    public Observable<ReGeoResult.ReGeoInfo> fetchLocationInfo(String latLng) {
        if (mService == null) {
            mService = new ApiClient().createApi("https://restapi.amap.com/v3/", ReGeoService.class);
        }
        return mService.reGeoGet(latLng)
                .map(result -> {
                    if (result == null || !"1".equals(result.getStatus())) {
                        throw new ApiException(CODE_FAILED, "请求数据失败");
                    } else if (result.getRegeocode() == null) {
                        throw new ApiException(CODE_FAILED, "请求数据失败");
                    } else if (result.getRegeocode().addressComponent == null) {
                        throw new ApiException(CODE_FAILED, "请求数据失败");
                    }
                    return result.getRegeocode().addressComponent;
                });
    }
}
