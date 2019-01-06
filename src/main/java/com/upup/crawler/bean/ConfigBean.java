package com.upup.crawler.bean;

public class ConfigBean {

    /**
     * redis_address : 120.76.62.144
     * redis_port : 6379
     * name : chhk
     * agency : false
     * thread : 200
     */

    private String redis_address;
    private int redis_port;
    private String name;
    private boolean agency;
    private int thread;

    public String getRedis_address() {
        return redis_address;
    }

    public void setRedis_address(String redis_address) {
        this.redis_address = redis_address;
    }

    public int getRedis_port() {
        return redis_port;
    }

    public void setRedis_port(int redis_port) {
        this.redis_port = redis_port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAgency() {
        return agency;
    }

    public void setAgency(boolean agency) {
        this.agency = agency;
    }

    public int getThread() {
        return thread;
    }

    public void setThread(int thread) {
        this.thread = thread;
    }

    @Override
    public String toString() {
        return "ConfigBean{" +
                "redis_address='" + redis_address + '\'' +
                ", redis_port=" + redis_port +
                ", name='" + name + '\'' +
                ", agency=" + agency +
                ", thread=" + thread +
                '}';
    }
}
