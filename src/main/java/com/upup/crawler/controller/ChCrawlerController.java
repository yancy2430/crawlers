package com.upup.crawler.controller;

import com.upup.crawler.service.ReptileService;
import com.upup.crawler.utils.Statics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class ChCrawlerController {
    @Resource(name = "chReptileServiceImpl")
    ReptileService reptileService;
    @RequestMapping(value = "ch/start")
    private String start(Integer thread) {
        if (Statics.isOpenCh){
            return "爬虫已经开启";
        }
        if (null==thread || thread<=0){
            thread =200;
        }
        Statics.isOpenCh = true;
        for (int i = 0; i < thread; i++) {
            new Thread("爬虫线程"+i) {
                @Override
                public void run() {
                    log.info("开启"+this.getName());
                    while (true) {
                        if (!Statics.isOpenCh) {
                            break;
                        }
                        try {
                            reptileService.getFlight();
                        }catch (Exception e){
                            log.info("抓取失败");
                        }
                    }
                    log.info("停止"+this.getName());
                }
            }.start();
        }
        log.info("爬虫启动成功");
        return "爬虫启动成功";
    }

    @RequestMapping(value = "ch/stop")
    private String stop() {
        Statics.isOpenCh = false;
        return "爬虫停止成功";
    }
}
