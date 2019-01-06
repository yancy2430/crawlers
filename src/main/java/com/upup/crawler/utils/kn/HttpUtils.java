package com.upup.crawler.utils.kn;

import okhttp3.OkHttpClient;

public enum HttpUtils {
    IN;
    private OkHttpClient client;
    HttpUtils() {
//        MyProxySelector myProxySelector = new MyProxySelector();
        client = new OkHttpClient();
    }

    public OkHttpClient getClient() {
        return client;
    }

}

