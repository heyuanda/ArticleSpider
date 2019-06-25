package com.meifang.article;

import com.meifang.article.service.NewPipeline;
import com.meifang.article.webMagic.HttpClientDownloader;
import com.meifang.article.webMagic.IfengProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

@Component
public class NewScheduled {
    private Logger logger= LoggerFactory.getLogger(NewScheduled.class);
    //定时爬虫方法
//    @Scheduled(cron="0 10 0/1 * * ?")
//    public void IfengScheduled(){
//        logger.info("开始爬虫");
//        Spider spider = Spider.create(new IfengProcessor()).setDownloader(new HttpClientDownloader());
//        spider.addUrl("https://house.ifeng.com//news");
//        spider.addPipeline(new NewPipeline());
//        spider.thread(5);
//        spider.setExitWhenComplete(true);
//        spider.start();
//        spider.stop();
//        logger.info("结束爬虫");
//    }
}
