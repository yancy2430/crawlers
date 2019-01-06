package com.upup.crawler.service;

public interface RedisService {

    /**
     * set存数据
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, String value);
    /**
     * set存数据
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, String value, long expire);

    /**
     * get获取数据
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 设置有效天数
     * @param key
     * @param expire
     * @return
     */
    boolean expire(String key, long expire);

    /**
     * 移除数据
     * @param key
     * @return
     */
    boolean remove(String key);

    /**
     * 左侧添加一个元素
     * @param key 列名
     * @param value 值
     * @return
     */
    boolean lpush(String key, String value);
    /**
     * 队列长度
     * @param key 队列名
     * @return
     */
    long llen(String key);
    /**
     * 右侧弹出一个元素
     * @param key 队列长度
     * @return
     */
    String rpop(String key);


    boolean expireByDay(final String key, long expire);
    /**
     * 添加一个集合
     * @param key 集合名
     * @param value 值
     * @return
     */
    boolean sadd(String key, String value);
    /**
     * 移除并返回集合中的一个随机元素
     * @param key 集合名
     * @param count 值
     * @return
     */
    String srand(String key, long count);
    /**
     * 判断一个元素是否已经在集合里
     * @param key 集合名
     * @param value 值
     * @return
     */
    boolean ismember(String key, String value);
}