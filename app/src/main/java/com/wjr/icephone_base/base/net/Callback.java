package com.wjr.icephone_base.base.net;


/**
 * created by @文景睿
 * description：回调接口
 */
public interface Callback<T> {

    void onResponse(T data);

    void onFailure(Throwable error);
}
