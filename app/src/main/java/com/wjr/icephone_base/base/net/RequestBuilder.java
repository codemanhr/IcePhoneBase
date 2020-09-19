package com.wjr.icephone_base.base.net;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;

/**
 * created by @文景睿
 * description：请求构建器
 */
public class RequestBuilder {
    private Class clazz;
    private String url;
    private Map<String, Object> params;


    public RequestBuilder(String url) {
        this.url = url;
        params = new HashMap<>();
    }


    public RequestBuilder addParam(String key, Object value) {
        params.put(key, value);
        return this;
    }


    public <T> RequestBuilder asObject(Class<T> clazz) {
        this.clazz = clazz;
        return this;
    }


    @SuppressWarnings("unchecked")
    public <T> void subscribeGet(Callback<T> callback) {
        if (clazz == null) {
            throw new NullPointerException("请调用asObject方法设置实体");
        }
        RxHttp.get(url)
                .addAll(params)
                .asString()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("Debug:::接口地址：", url);
                        Log.e("Debug 返回数据", s);
                        Log.e("Debug:::", "===============================================================");
                        T t = (T) JSON.parseObject(s, clazz);

                        callback.onResponse(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Debug:::接口地址：", url);
                        Log.e("Debug 原因", e.toString());
                        Log.e("Debug:::", "===============================================================");
                        callback.onFailure(e.getCause());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @SuppressWarnings("unchecked")
    public <T> void subscribePost(Callback<T> callback) {
        if (clazz == null) {
            throw new NullPointerException("请调用asObject方法设置实体");
        }
        RxHttp.postJson(url)
                .addAll(params)
                .asString()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("Debug:::接口地址：", url);
                        Log.e("Debug 返回数据", s);
                        Log.e("Debug:::", "===============================================================");
                        T t = (T) JSON.parseObject(s, clazz);
                        callback.onResponse(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Debug:::接口地址：", url);
                        Log.e("Debug 原因", e.toString());
                        Log.e("Debug:::", "===============================================================");
                        callback.onFailure(e.getCause());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @SuppressWarnings("unchecked")
    public <T> void subscribePostByUrl(Callback<T> callback) {
        if (clazz == null) {
            throw new NullPointerException("请调用asObject方法设置实体");
        }
        RxHttp.postJson(url)
                .add("", "")/**Add by@马俊豪 加了一个空的json，不加的时候车辆控制接口报错，显示requestBody为空**/
                .asString()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("Debug:::接口地址：", url);
                        Log.e("Debug 返回数据", s);
                        Log.e("Debug:::", "===============================================================");
                        T t = (T) JSON.parseObject(s, clazz);
                        callback.onResponse(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Debug:::接口地址：", url);
                        Log.e("Debug 原因", e.toString());
                        Log.e("Debug:::", "===============================================================");
                        callback.onFailure(e.getCause());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
