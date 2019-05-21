package com.xw.project.gracefulmovies.viewmodel.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * <p>
 * Created by woxingxiao on 2018-08-27.
 */
public abstract class BaseViewModel extends ViewModel {

    private MutableLiveData<Boolean> mLoadLive = new MutableLiveData<>();

    public void load() {
        mLoadLive.setValue(true);
    }

    protected MutableLiveData<Boolean> getLoadLive() {
        return mLoadLive;
    }
}
