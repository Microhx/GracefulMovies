package com.xing.project.movie.data.ao.bridge;

import com.google.gson.annotations.SerializedName;

/**
 * <p>
 * Created by woxingxiao on 2018-08-21.
 */

public class ModelBridge<T> {

    @SerializedName(value = "code", alternate = "error_code")
    public int code;
    @SerializedName(value = "msg", alternate = "reaseon")
    public String message;
    @SerializedName(value = "data", alternate = {"ms", "attention"})
    public T data;

}
