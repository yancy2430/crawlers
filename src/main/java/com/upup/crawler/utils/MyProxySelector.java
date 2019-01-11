package com.upup.crawler.utils;

import com.upup.crawler.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * 代理IP选择
 */
@Component
public class MyProxySelector extends ProxySelector {
    List<Proxy> proxies = new ArrayList<>();
    Map<String, Proxy> proxyMap = new HashMap<>();
    @Autowired
    RedisService redisService;
    @Override
    public List<Proxy> select(URI uri) {
        proxies.clear();
        System.err.println(redisService);
        String string = redisService.srand("ip_pool",1);
        String[] ip = string.split(":");
        proxies.add(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip[0], Integer.parseInt(ip[1]))));
        return proxies;
    }
    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        proxyMap.remove(sa.toString().replace("/","").split(":")[0]);
    }
}
