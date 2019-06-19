package com.xing.project.movie.data.api;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.xing.project.movie.BuildConfig;
import com.xing.project.movie.util.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>
 * Created by woxingxiao on 2018-08-18.
 */
public class ApiClient {

    private Retrofit.Builder mRetrofitBuilder;
    private OkHttpClient.Builder mOkHttpClientBuilder;


    public ApiClient() {
        mRetrofitBuilder = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //https://api-m.mtime.cn/
                .addConverterFactory(GsonConverterFactory.create());


        mOkHttpClientBuilder = new OkHttpClient.Builder();
        mOkHttpClientBuilder.connectTimeout(15, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            mOkHttpClientBuilder.addNetworkInterceptor(
                    new LoggingInterceptor.Builder()
                            .loggable(true)
                            .setLevel(Level.BODY)
                            .log(Platform.INFO)
                            .request("Request")
                            .response("Response")
                            .build()
            );
        }
    }

    public <S> S createApi(String baseUrl, Class<S> ApiClass) {
        mRetrofitBuilder.baseUrl(baseUrl);

        return createApi(ApiClass);
    }

    public <S> S createApi(Class<S> ApiClass) {
        return mRetrofitBuilder
                .client(mOkHttpClientBuilder.build())
                .build()
                .create(ApiClass);
    }

}
