package com.upup.crawler.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StrUtils {

    /**
     * 参数转json
     * @param string
     * @return
     */
    public static String paramTojson(String string){
        Map<String,String> stringMap = new HashMap<String, String>();
        String[] strs = string.split("&");
        for (String str : strs) {
            String[] sts = new String[]{"",""};
            String[] st = str.split("=");
            if (st.length==1){
                sts[0] = st[0];
            }else {
                sts[0] = st[0];
                sts[1] = st[1];
            }
            stringMap.put(sts[0],sts[1]);
        }
        return new Gson().toJson(stringMap);
    }
    /**
     * bean转json
     * @param string
     * @return
     */
    public static String beanTojson(Object string){
        return new Gson().toJson(string);
    }
    /**
     * bean转参数
     * @param object
     * @return
     */
    public static String beanToParam(Object object){
        return jsonToParam(new Gson().toJson(object));
    }
    /**
     * json转参数
     * @param string
     * @return
     */
    public static String jsonToParam(String string){
        Map<String,String> map = new Gson().fromJson(string, Map.class);
        String str = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            str+=entry.getKey()+"="+entry.getValue()+"&";
        }
        str = str.substring(0,str.length()-1);
        return str;
    }
    /**
     * json转bean
     * @param string
     * @return
     */
    public static Object jsonTobean(String string,Class<?> t){
        return new Gson().fromJson(string,t);
    }

    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }


    /**
     * bean 转sql插入
     */
    public static String jsonToListSql(String json,String tb){
        Type type = new TypeToken<List<Map<String,Object>>>(){}.getType();
        List<Map<String,Object>> list = new Gson().fromJson(json,type);
        String str = "INSERT INTO "+tb+" (";
        String sss ="";
        for (Map.Entry<String, Object> entry : list.get(0).entrySet()) {
            if (null!=entry.getValue() && !entry.getValue().toString().equals("0.0")) {
                sss += "`" + entry.getKey() + "`,";
            }
        }
        sss = sss.substring(0,sss.length()-1);
        str+=sss;
        str+=") VALUES ";
        for (Map<String, Object> map : list) {
            str+="(";
            String bbb ="";
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (null!=entry.getValue() && !entry.getValue().toString().equals("0.0")) {
                    bbb += "'" + entry.getValue() + "',";
                }
            }
            bbb = bbb.substring(0,bbb.length()-1);
            str+=bbb;
            str+="),";
        }
        str = str.substring(0,str.length()-1);
        System.err.println(str);
        try {
            return new String(str.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * bean 转sql插入
     */
    public static String jsonToSql(String json,String tb){
        Map<String,Object> map = new Gson().fromJson(json, Map.class);
        String str = "INSERT INTO "+tb+" (";
        String sss ="";
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (null!=entry.getValue() && !entry.getValue().toString().equals("0.0")) {
                sss += "`" + entry.getKey() + "`,";
            }
        }
        sss = sss.substring(0,sss.length()-1);
        str+=sss;
        str+=") VALUES (";
        String bbb ="";
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (null!=entry.getValue() && !entry.getValue().toString().equals("0.0")) {
                bbb += "'" + entry.getValue() + "',";
            }
        }
        bbb = bbb.substring(0,bbb.length()-1);
        str+=bbb;
        str+=")";
        try {
            return new String(str.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String jsonToCreate(String json,String tb){
        Map<String,Object> map = new Gson().fromJson(json, Map.class);
        String str = "CREATE TABLE `"+tb+"` ( `id` int(11) unsigned NOT NULL AUTO_INCREMENT,";
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            str+="`"+entry.getKey()+"` varchar(200) DEFAULT NULL,";
        }
        str+=" PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;";
        return str;
    }


    /**
     * bean 转sql插入
     */
    public static String jsonToUpdateSql(String json,String tb,String where){
        Map<String,Object> map = new Gson().fromJson(json, Map.class);
        String str = "UPDATE "+tb+" set ";
        String sss ="";
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (null!=entry.getValue() && !entry.getValue().toString().equals("0.0")) {
                sss += "`" + entry.getKey().toString() + "`" + "='" + entry.getValue() + "',";
            }
        }
        sss = sss.substring(0,sss.length()-1);
        str+=sss;
        str+=" where "+where;
//        System.err.println(str);
        return str;
    }

    public static void main(String[] args) {
//        Qnracess qnracess = new Gson().fromJson("{\"lasttime\":\"2018-11-08 15:23:49 \",\"dpt\":\"CGQ\",\"arr\":\"SZX\",\"dptAirport\":\"CGQ\",\"arrAirPort\":\"SZX\",\"date\":\"2000-10-02\",\"carrier\":\"9C\",\"code\":\"9C8656\",\"dptTime\":\"2000\",\"arrTime\":\"0125\",\"planeType\":\"A320\",\"airportconstruction\":50,\"fuelsurcharge\":30,\"stops\":0,\"seatsForJinji\":\"A\",\"meal\":0,\"cabinYPrice\":2750,\"jinjiCabinCode\":\"R3\",\"ageLimit\":\"0-70\",\"appjinjiPrice\":\"770\",\"weekNumber\":\"3\",\"dptStation\":\"龙嘉国际机场\",\"arrStation\":\"宝安国\"}",Qnracess.class);
//        qnracess.setArrStation(null);
//        qnracess.setDptStation(null);
        //        String str = jsonToUpdateSql(new Gson().toJson(qnracess)," qnracess ","dptAirport='"+qnracess.getDptAirport()+"' and arrAirPort='"+qnracess.getArrAirPort()+"' and `date`='"+qnracess.getDate()+"' and `code`='"+qnracess.getCode()+"'");
//        DbUtil.IN.executparsing(qnracess,"qnracess");
        //        System.err.println(str);
    }

    public static String getRandom(int num){
        String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
        Random rand = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < num; j++)
        {
            flag.append(sources.charAt(rand.nextInt(9)) + "");
        }
        return flag.toString();
    }
    public static String getRandomIp() {

        // ip范围
        int[][] range = { { 607649792, 608174079 }, // 36.56.0.0-36.63.255.255
                { 1038614528, 1039007743 }, // 61.232.0.0-61.237.255.255
                { 1783627776, 1784676351 }, // 106.80.0.0-106.95.255.255
                { 2035023872, 2035154943 }, // 121.76.0.0-121.77.255.255
                { 2078801920, 2079064063 }, // 123.232.0.0-123.235.255.255
                { -1950089216, -1948778497 }, // 139.196.0.0-139.215.255.255
                { -1425539072, -1425014785 }, // 171.8.0.0-171.15.255.255
                { -1236271104, -1235419137 }, // 182.80.0.0-182.92.255.255
                { -770113536, -768606209 }, // 210.25.0.0-210.47.255.255
                { -569376768, -564133889 }, // 222.16.0.0-222.95.255.255
        };

        Random rdint = new Random();
        int index = rdint.nextInt(10);
        String ip = num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
        return ip;
    }
    /*
     * 将十进制转换成IP地址
     */
    public static String num2ip(int ip) {
        int[] b = new int[4];
        String x = "";
        b[0] = (int) ((ip >> 24) & 0xff);
        b[1] = (int) ((ip >> 16) & 0xff);
        b[2] = (int) ((ip >> 8) & 0xff);
        b[3] = (int) (ip & 0xff);
        x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);

        return x;
    }

    /**
     * 日期转星期
     * @param datetime
     * @return
     */
    public static int dateToWeek (String datetime){
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        try {
            cal.setTime(f.parse(datetime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return Integer.parseInt(weekDays[w]);
    }
}
