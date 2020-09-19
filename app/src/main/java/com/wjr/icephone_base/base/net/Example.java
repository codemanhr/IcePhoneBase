package com.wjr.icephone_base.base.net;


/**
 * created by @文景睿
 * description：例子
 */
public class Example {
    public void example() {
        ApiClient.getInstance()
                .withUrl("feofnewnfowfwenfw")
                .addParam("key","value")
                .addParam("key2","value2")
                .asObject(ExampleBean.class)
                .subscribeGet(new Callback<ExampleBean>() {
                    @Override
                    public void onResponse(ExampleBean data) {
                        /**  这里已经是主线程  **/
                    }

                    @Override
                    public void onFailure(Throwable error) {

                    }
                });
    }

}
