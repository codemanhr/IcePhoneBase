package com.wjr.icephone_base.base.net;

/**
 * created by @文景睿
 * description：网络请求封装
 */
public class ApiClient {
    private static ApiClient netClient;

    private ApiClient() {

    }

    public static ApiClient getInstance() {
        if (netClient == null) {
            synchronized (ApiClient.class) {
                if (netClient == null) {
                    netClient = new ApiClient();
                }
            }
        }
        return netClient;
    }



    public RequestBuilder withUrl(String url) {
        return new RequestBuilder(url);
    }
}
