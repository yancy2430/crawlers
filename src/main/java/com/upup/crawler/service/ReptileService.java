package com.upup.crawler.service;


import com.upup.crawler.bean.Flight;
import okhttp3.MediaType;

import java.net.Proxy;

/**
 * 爬虫抓取数据接口
 */
public interface ReptileService {
    /**
     * 解析航线数据
     * @param flight
     * @return
     */
    boolean analysis(String flight) throws Exception;
    /**
     * 获取航班参数
     * @return
     */
    boolean getFlight() throws Exception;


    /**
     * 请求数据类型
     */
    class Type{
        public final static String POST = "POST";
        public final static String GET = "GET";
        public final static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        public final static MediaType FORM = MediaType.parse("application/x-www-form-urlencoded");
    }
}
