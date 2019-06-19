package com.xing.project.movie;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.xing.project.movie.data.db.GMDatabase;
import com.xing.project.movie.repository.CityRepository;
import com.xing.project.movie.rx.RxSchedulers;
import com.xing.project.movie.rx.SimpleConsumer;
import com.xing.project.movie.util.Logy;

import io.reactivex.Observable;

/**
 * <p>
 * Created by woxingxiao on 2018-08-14.
 */
public class GMApplication extends Application {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static boolean DEBUG = true;
    public static GMApplication sApplication;

    
    private GMDatabase mDatabase;
    private CityRepository mCityRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        sApplication = this;

        Observable.just("")
                .compose(RxSchedulers.applyIO())
                .subscribe(new SimpleConsumer<String>() {
                    @Override
                    public void accept(String it) {
                        mDatabase = GMDatabase.createAsync(sApplication);
                    }
                });
        Observable.just("")
                .compose(RxSchedulers.applyIO())
                .subscribe(new SimpleConsumer<String>() {
                    @Override
                    public void accept(String it) {
                        Logy.init(GMApplication.DEBUG);
//                        CrashHandler.getInstance().init(getApplicationContext());
                    }
                });

        addViewPumpSetting();
    }

  private void addViewPumpSetting() {
     // ViewPump.init(ViewPump.builder().build());
  }

  public static GMApplication getInstance() {
        return sApplication;
    }

    public GMDatabase getDatabase() {
        return mDatabase;
    }

    public CityRepository getCityRepository() {
        if (mCityRepository == null) {
            mCityRepository = new CityRepository();
        }
        return mCityRepository;
    }
}
