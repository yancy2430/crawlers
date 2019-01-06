package com.upup.crawler.service.impl;


import com.upup.crawler.bean.ch.Qnracess;
import com.upup.crawler.bean.ch.ReqBaseBean;
import com.upup.crawler.bean.ch.ReqFlight;
import com.upup.crawler.bean.ch.RspeFlight;
import com.upup.crawler.service.RedisService;
import com.upup.crawler.service.ReptileService;
import com.upup.crawler.utils.MD5Utils;
import com.upup.crawler.utils.StrUtils;
import com.upup.crawler.utils.ch.DESUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class ChReptileServiceImpl implements ReptileService{
    static String reqBaseString ="{\"isEmulator\":\"n\",\"remoteIP\":\""+StrUtils.getRandomIp()+"\",\"latitude\":\""+StrUtils.getRandom(2)+"."+StrUtils.getRandom(4)+"\",\"deviceSNPlus\":\""+ MD5Utils.MD5Encode(StrUtils.getRandom(20),"UTF-8")+"\",\"subTerminalType\":\"Android\",\"deviceSN\":\""+StrUtils.getRandom(20)+"\",\"terminalType\":\"g\",\"const_id\":\"\",\"moneyClassId\":\"0\",\"versionNo\":\"6.5.4\",\"lang\":\"zh_CN\",\"channelid\":\"1\",\"channelId\":\"-2\",\"locationCity\":\"山东\",\"longitude\":\""+StrUtils.getRandom(2)+"."+StrUtils.getRandom(4)+"\"}";
    static ReqBaseBean reqBaseBean = (ReqBaseBean) StrUtils.jsonTobean(reqBaseString, ReqBaseBean.class);
    //春秋域名
    private static final String url = "https://app.ch.com";
    private static final String key = "chhk";
    //获取秘钥 请求参数为
    private static final String flightQuery = url + "/ECommercePlatform/m/flightV651/query?";
    @Autowired
    RedisService redisService;
    @Autowired
    OkHttpClient okHttpClient;
    @Override
    public boolean getFlight() {
        ReqFlight reqFlight = new ReqFlight();
        String string = redisService.rpop(key);
        String[] split = string.replace("\"","").split("\\|");
        reqFlight.setChannelId(reqBaseBean.getChannelid());
        reqFlight.setIsEmulator(reqBaseBean.getIsEmulator());
        reqFlight.setLatitude(reqBaseBean.getLatitude());
        reqFlight.setLongitude(reqBaseBean.getLongitude());
        reqFlight.setVersionNo(reqBaseBean.getVersionNo());
        reqFlight.setLang(reqBaseBean.getLang());
        reqFlight.setDeviceSNPlus(reqBaseBean.getDeviceSNPlus());
        reqFlight.setDeviceSN(reqBaseBean.getDeviceSN());
        reqFlight.setSubTerminalType(reqBaseBean.getSubTerminalType());
        reqFlight.setLocationCity(reqBaseBean.getLocationCity());
        reqFlight.setRemoteIP(reqBaseBean.getRemoteIP());
        reqFlight.setTerminalType(reqBaseBean.getTerminalType());
        reqFlight.setMoneyClassId(reqBaseBean.getMoneyClassId());
        reqFlight.setChannelid(reqBaseBean.getChannelid());
        reqFlight.setChannelId(reqBaseBean.getChannelId());
        reqFlight.setUserId("");
        reqFlight.setLoginId("");
        reqFlight.setCabin("0");//舱位
        reqFlight.setCompanyId("0");//公司标识
        reqFlight.setFlightDateGo("");//
        reqFlight.setOriCityCode(split[0]);//出发城市三字码
        reqFlight.setDestCityCode(split[1]);//到达城市三字码
        reqFlight.setTermId("-1");
        reqFlight.setIsNewProduct("Y");//是否新航线
        reqFlight.setWfFlag("0");
        reqFlight.setSaleTypeDetail("4");
        reqFlight.setFlightDate(split[2]);
        reqFlight.setLcNo("1");
        reqFlight.setFlightType("1");
        reqFlight.setCustType("1");
        try {
            return analysis(okHttpClient.newCall(new Request.Builder().url(flightQuery).post(RequestBody.create(Type.FORM, "data=" + URLEncoder.encode(DESUtils.encode(StrUtils.beanTojson(reqFlight)), "UTF-8"))).build()).execute().body().string());
        } catch (Exception e) {
            throw new RuntimeException("加密异常");
        }
    }

    @Override
    public boolean analysis(String flight) {
        long a = System.currentTimeMillis();
        try {
            flight = DESUtils.decode(flight);
        } catch (Exception e) {
            new RuntimeException("解析异常");
        }
        //判断返回内容是否正确 如果不正确则抛异常
        RspeFlight rspeFlight = (RspeFlight) StrUtils.jsonTobean(flight, RspeFlight.class);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (RspeFlight.FlightQueryResultsBean flightQueryResult : rspeFlight.getFlightQueryResults()) {
            Qnracess qnracess = new Qnracess();
            qnracess.setLasttime(fmt.format(LocalDateTime.now()));
            qnracess.setDpt(flightQueryResult.getOriCityCode());
            qnracess.setArr(flightQueryResult.getArvCityCode());
            qnracess.setDptAirport(flightQueryResult.getOriAirportCode());
            qnracess.setArrAirPort(flightQueryResult.getArvAirportCode());
            qnracess.setDate(flightQueryResult.getOriDate());
            qnracess.setCarrier("9C");
            qnracess.setCode(flightQueryResult.getFlightNO());
            qnracess.setDptTime(flightQueryResult.getOriTime().replace(":", ""));
            qnracess.setArrTime(flightQueryResult.getArvTime().replace(":", ""));
            qnracess.setPlaneType(flightQueryResult.getAircraft());
            qnracess.setAirportconstruction(flightQueryResult.getAirportFee());
            qnracess.setFuelsurcharge(flightQueryResult.getFuelFee());
            Map<String, RspeFlight.FlightQueryResultsBean.MoreCabinBeanBean.NormCabinBeansBean> map = new HashMap<>();
            for (RspeFlight.FlightQueryResultsBean.MoreCabinBeanBean.NormCabinBeansBean normCabinBean : flightQueryResult.getMoreCabinBean().getNormCabinBeans()) {
                if (normCabinBean.getCabinLevel().equals("A") || normCabinBean.getCabinDisName().contains("会员专享")) {
                    if (normCabinBean.getRemainTicketCount() < 0) {
                        qnracess.setSeatsForSale("10");
                    } else {
                        qnracess.setSeatsForSale("" + normCabinBean.getRemainTicketCount());
                    }
                    qnracess.setSaleCabinCode(normCabinBean.getCabinName());
                    qnracess.setAppCabinPrice(normCabinBean.getCabinPrice());
                } else if (normCabinBean.getCabinLevel().equals("CGroup") || normCabinBean.getCabinDisName().contains("经济座")) {
                    if (normCabinBean.getRemainTicketCount() < 0) {
                        qnracess.setSeatsForJinji("10");
                    } else {
                        qnracess.setSeatsForJinji("" + normCabinBean.getRemainTicketCount());
                    }
                    qnracess.setJinjiCabinCode(normCabinBean.getCabinName());
                    qnracess.setAppjinjiPrice(normCabinBean.getCabinPrice());
                }
            }
            qnracess.setCabinYPrice(flightQueryResult.getPublicPrice());
            qnracess.setAgeLimit("0-70");
            qnracess.setWeekNumber(StrUtils.dateToWeek(qnracess.getDate()));
            qnracess.setDptStation(flightQueryResult.getOriAirport());
            qnracess.setArrStation(flightQueryResult.getArvAirport());
            if (StringUtils.isBlank(qnracess.getSeatsForSale())) {
                qnracess.setSeatsForSale("-1");
                qnracess.setAppCabinPrice((double) 0);
            }
            if (StringUtils.isBlank(qnracess.getSeatsForJinji())) {
                qnracess.setSeatsForJinji("-1");
                qnracess.setAppjinjiPrice((double) 0);
            }
            qnracess.setIsapp(2);
            String key = qnracess.getDate()+"-"+qnracess.getCode()+"-"+qnracess.getDptAirport()+"-"+qnracess.getArrAirPort()+"-"+qnracess.getCarrier();
            qnracess.setId(key);
            redisService.set(key,StrUtils.beanTojson(qnracess),1);
            redisService.expireByDay(key,dayDiff(flightQueryResult.getOriDate(),new java.text.SimpleDateFormat("yyyy-MM-dd").format(java.util.Calendar.getInstance().getTime()),"yyyy-MM-dd"));
            log.info("爬取成功："+key);

            return true;
        }
        return false;
    }
    /**
     * @descript:计算两个字符串日期相差的天数
     * @param date1 字符串日期1
     * @param date2 字符串日期2
     * @param format 日期格式化方式  format="yyyy-MM-dd"
     * @return
     */
    public static long dayDiff(String date1, String date2,String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        long diff=0l;
        try {
            long d1 = formater.parse(date1).getTime();
            long d2 = formater.parse(date2).getTime();
            //diff=(Math.abs(d1-d2) / (1000 * 60 * 60 * 24));
            diff=(d1-d2)/(1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

    public static void main(String[] args) {
    }
}
