package com.upup.crawler.service.impl;

import com.google.gson.Gson;
import com.upup.crawler.bean.kn.AppInfoBean;
import com.upup.crawler.bean.kn.FlightSearch;
import com.upup.crawler.bean.kn.RequestJson;
import com.upup.crawler.bean.kn.ResponseJson;
import com.upup.crawler.service.RedisService;
import com.upup.crawler.service.ReptileService;
import com.upup.crawler.utils.StrUtils;
import com.upup.crawler.utils.ch.DESUtils;
import com.upup.crawler.utils.kn.Base64Utils;
import com.upup.crawler.utils.kn.KNDESUtil;
import com.upup.crawler.utils.kn.RSAUtils;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Date;
@Service
public class KnReptileServiceImpl implements ReptileService {
    private static final String DEFAULT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaC6meusPysktDVatRM5uRO6Jwzdr9lJI7U7NTrhJNzfPnzjHQ6jF6r+baBJUsdZtJkgu9gJMABUPyP2qV49iIrem1H8AP3azPLRLNQJ2CNA6pNHrgFMAJ9DuKbwMmBCWSqsof7CEmbeQLxu+cStGCfagpYr0l+pavoiE9Sz2WxQIDAQAB";
    private static String str = "{\"os\":\"android;1080x1920;6.0\",\"version\":\"8.2.5\",\"hardware\":\"0;2051740k;Y=1080.0,X=1794.0\",\"ssoKey\":\"as_1ilidawh\",\"deviceCode\":\"deviceCode\"}";
    private static final String key = "knhk";
    static Gson gson = new Gson();
    static AppInfoBean appInfoBean = null;
    @Autowired
    RedisService redisService;
    @Autowired
    OkHttpClient okHttpClient;
    public KnReptileServiceImpl(){

    }


    @Override
    public boolean getFlight() throws Exception {
        if(null==appInfoBean){
            getSafeKey();
        }
        String string = redisService.rpop(key);
        String[] split = string.replace("\"","").split("\\|");
        FlightSearch flightSearch = new FlightSearch();
        flightSearch.setOrgCode(split[0]);
        flightSearch.setDstCode(split[1]);
        flightSearch.setTakeoffdate1(split[2]);
        flightSearch.setTakeoffdate2("");
        flightSearch.setTripType("OW");
        RequestJson requestJson = new RequestJson();
        requestJson.setRequestTime(new Date().getTime());
        requestJson.setSafety(true);
        requestJson.setSessionKey(appInfoBean.getSessionKey());
        requestJson.setRequestJSON(KNDESUtil.encrypt(new Gson().toJson(flightSearch), appInfoBean.getSafeKey()));
        analysis(okHttpClient.newCall(new Request.Builder().url("http://app.flycua.com:80/ewp/pip/book/flightSearch.html").post(RequestBody.create(Type.FORM, "data=" + URLEncoder.encode(DESUtils.encode(StrUtils.beanTojson(requestJson)), "UTF-8"))).build()).execute().body().string());

        return false;
    }
    @Override
    public boolean analysis(String flight) throws Exception {
        ResponseJson respon = new Gson().fromJson(flight, ResponseJson.class);
        String s = KNDESUtil.decryptor(respon.getResponseJSON(), appInfoBean.getSafeKey());
        System.err.println(s);
        return false;
    }

    public void getSafeKey() throws Exception {
        String resp = okHttpClient.newCall(new Request.Builder().url("http://app.flycua.com:80/ewp/touch.html").post(RequestBody.create(Type.JSON,encrypt(str))).build()).execute().body().string();
        System.err.println(resp);
        String sjson = decrypt(resp);
        appInfoBean = gson.fromJson(sjson, AppInfoBean.class);
        if (appInfoBean.isOk()) {
            System.err.println("获取配置数据：" + appInfoBean.toString());
        }
    }

    public static String encrypt(String str) throws Exception {
        byte[] encodedData = new byte[0];
        encodedData = RSAUtils.encryptByPublicKey(str.getBytes(), DEFAULT_PUBLIC_KEY);
        return Base64Utils.encode(encodedData);
    }

    public static String decrypt(String str) throws Exception {
        byte[] data2 = new byte[0];
        data2 = Base64Utils.
                decode(str);
        byte[] encodedData2 = new byte[0];
        encodedData2 = RSAUtils.decryptByPublicKey(data2, DEFAULT_PUBLIC_KEY);
        return new String(encodedData2);
    }

}
