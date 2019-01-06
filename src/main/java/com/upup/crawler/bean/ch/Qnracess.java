package com.upup.crawler.bean.ch;

public class Qnracess {

    /**
     * 主键
     *
     * isNullAble:0
     */
    private String id;

    /**
     * 更新时间<find><between><date>
     * isNullAble:1
     */
    private String lasttime;

    /**
     *
     * isNullAble:1
     */
    private String dpt;

    /**
     *
     * isNullAble:1
     */
    private String arr;

    /**
     * 出发<find>
     * isNullAble:1
     */
    private String dptAirport;

    /**
     * 到达<find>
     * isNullAble:1
     */
    private String arrAirPort;

    /**
     * 日期<find><between><date>
     * isNullAble:1
     */
    private String date;

    /**
     * 航司<find>
     * isNullAble:1,defaultVal:9C
     */
    private String carrier;

    /**
     * 航班号<find>
     * isNullAble:1
     */
    private String code;

    /**
     * 起飞<find><between>
     * isNullAble:1
     */
    private String dptTime;

    /**
     * 到达<find><between>
     * isNullAble:1
     */
    private String arrTime;

    /**
     * 机型<find><like><hidden>
     * isNullAble:1
     */
    private String planeType;

    /**
     * 机场建设费<hidden>
     * isNullAble:1
     */
    private Double airportconstruction;

    /**
     * 燃油费<hidden>
     * isNullAble:1
     */
    private Double fuelsurcharge;

    /**
     *
     * isNullAble:1,defaultVal:0
     */
    private Integer stops;

    /**
     *
     * isNullAble:1
     */
    private String codeShare;

    /**
     * 余位<find><between>
     * isNullAble:1
     */
    private String seatsForSale;

    /**
     * 经济余位<find><between>
     * isNullAble:1
     */
    private String seatsForJinji;

    /**
     *
     * isNullAble:1,defaultVal:0
     */
    private Integer meal;

    /**
     * Y舱价<find><between>
     * isNullAble:1
     */
    private Double cabinYPrice;

    /**
     *
     * isNullAble:1
     */
    private Double cablnCPrice;

    /**
     *
     * isNullAble:1
     */
    private Double cabinFPrice;

    /**
     * 舱位<find>
     * isNullAble:1
     */
    private String saleCabinCode;

    /**
     * 经济舱位<find>
     * isNullAble:1
     */
    private String jinjiCabinCode;

    /**
     *
     * isNullAble:1
     */
    private Double cpcSalePrice;

    /**
     *
     * isNullAble:1
     */
    private Double cpaSalePrice;

    /**
     * 年龄限制<find><like><hidden>
     * isNullAble:1
     */
    private String ageLimit;

    /**
     * 直享<find><between>
     * isNullAble:1
     */
    private Double printPrice;

    /**
     * 经济仓价<find><between>
     * isNullAble:1
     */
    private Double jinjiPrice;

    /**
     * 编号
     * isNullAble:1
     */
    private Integer pos;

    /**
     * app直享价<hidden>
     * isNullAble:1
     */
    private Double appCabinPrice;

    /**
     * app经济舱价<hidden>
     * isNullAble:1
     */
    private Double appjinjiPrice;

    /**
     * 星期<hidden>
     * isNullAble:1
     */
    private Integer weekNumber;

    /**
     * 出发航站楼<hidden>
     * isNullAble:1
     */
    private String dptStation;

    /**
     * 到达航站楼<hidden>
     * isNullAble:1
     */
    private String arrStation;

    /**
     * 来源|[{"v":"1","t":"官网"},{"v":"2","t":"APP"}]<ssel><auto><find>
     * isNullAble:1,defaultVal:1
     */
    private Integer isapp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public String getDpt() {
        return dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }

    public String getArr() {
        return arr;
    }

    public void setArr(String arr) {
        this.arr = arr;
    }

    public String getDptAirport() {
        return dptAirport;
    }

    public void setDptAirport(String dptAirport) {
        this.dptAirport = dptAirport;
    }

    public String getArrAirPort() {
        return arrAirPort;
    }

    public void setArrAirPort(String arrAirPort) {
        this.arrAirPort = arrAirPort;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDptTime() {
        return dptTime;
    }

    public void setDptTime(String dptTime) {
        this.dptTime = dptTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getPlaneType() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public Double getAirportconstruction() {
        return airportconstruction;
    }

    public void setAirportconstruction(Double airportconstruction) {
        this.airportconstruction = airportconstruction;
    }

    public Double getFuelsurcharge() {
        return fuelsurcharge;
    }

    public void setFuelsurcharge(Double fuelsurcharge) {
        this.fuelsurcharge = fuelsurcharge;
    }

    public Integer getStops() {
        return stops;
    }

    public void setStops(Integer stops) {
        this.stops = stops;
    }

    public String getCodeShare() {
        return codeShare;
    }

    public void setCodeShare(String codeShare) {
        this.codeShare = codeShare;
    }

    public String getSeatsForSale() {
        return seatsForSale;
    }

    public void setSeatsForSale(String seatsForSale) {
        this.seatsForSale = seatsForSale;
    }

    public String getSeatsForJinji() {
        return seatsForJinji;
    }

    public void setSeatsForJinji(String seatsForJinji) {
        this.seatsForJinji = seatsForJinji;
    }

    public Integer getMeal() {
        return meal;
    }

    public void setMeal(Integer meal) {
        this.meal = meal;
    }

    public Double getCabinYPrice() {
        return cabinYPrice;
    }

    public void setCabinYPrice(Double cabinYPrice) {
        this.cabinYPrice = cabinYPrice;
    }

    public Double getCablnCPrice() {
        return cablnCPrice;
    }

    public void setCablnCPrice(Double cablnCPrice) {
        this.cablnCPrice = cablnCPrice;
    }

    public Double getCabinFPrice() {
        return cabinFPrice;
    }

    public void setCabinFPrice(Double cabinFPrice) {
        this.cabinFPrice = cabinFPrice;
    }

    public String getSaleCabinCode() {
        return saleCabinCode;
    }

    public void setSaleCabinCode(String saleCabinCode) {
        this.saleCabinCode = saleCabinCode;
    }

    public String getJinjiCabinCode() {
        return jinjiCabinCode;
    }

    public void setJinjiCabinCode(String jinjiCabinCode) {
        this.jinjiCabinCode = jinjiCabinCode;
    }

    public Double getCpcSalePrice() {
        return cpcSalePrice;
    }

    public void setCpcSalePrice(Double cpcSalePrice) {
        this.cpcSalePrice = cpcSalePrice;
    }

    public Double getCpaSalePrice() {
        return cpaSalePrice;
    }

    public void setCpaSalePrice(Double cpaSalePrice) {
        this.cpaSalePrice = cpaSalePrice;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public Double getPrintPrice() {
        return printPrice;
    }

    public void setPrintPrice(Double printPrice) {
        this.printPrice = printPrice;
    }

    public Double getJinjiPrice() {
        return jinjiPrice;
    }

    public void setJinjiPrice(Double jinjiPrice) {
        this.jinjiPrice = jinjiPrice;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public Double getAppCabinPrice() {
        return appCabinPrice;
    }

    public void setAppCabinPrice(Double appCabinPrice) {
        this.appCabinPrice = appCabinPrice;
    }

    public Double getAppjinjiPrice() {
        return appjinjiPrice;
    }

    public void setAppjinjiPrice(Double appjinjiPrice) {
        this.appjinjiPrice = appjinjiPrice;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public String getDptStation() {
        return dptStation;
    }

    public void setDptStation(String dptStation) {
        this.dptStation = dptStation;
    }

    public String getArrStation() {
        return arrStation;
    }

    public void setArrStation(String arrStation) {
        this.arrStation = arrStation;
    }

    public Integer getIsapp() {
        return isapp;
    }

    public void setIsapp(Integer isapp) {
        this.isapp = isapp;
    }
}
