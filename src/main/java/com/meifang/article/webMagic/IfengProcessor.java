package com.meifang.article.webMagic;

import com.meifang.article.dao.domain.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 凤凰新闻爬虫
 *
 * @author heyuanda
 * @date 2019.6.20
 */
public class IfengProcessor implements PageProcessor {
    private static Logger logger = LoggerFactory.getLogger(IfengProcessor.class);

    private static final String BASE_URL = "https://sh.house.ifeng.com/news";

    private Site site = Site.me().setRetryTimes(10).setSleepTime(1000).setTimeOut(30000);

    @Override
    public void process(Page page) {
        if ("https://house.ifeng.com/news".equals(page.getUrl())) return;
        if (page.getUrl().regex(BASE_URL).match()) {
            List<String> list = page.getHtml()
                    .xpath("//div[@id='city_con']//a/@href").all();
            for (String s : list) {
                //筛选爬取页面
                if (!("//house.ifeng.com/column/news/bczl2018".equals(s))) {
                    //添加所有城市的url到爬取队列
                    page.addTargetRequest("https:" + s + "/news");
                }
            }
            //手动添加北京地区的网址
            page.addTargetRequest("https://house.ifeng.com/news/1-4/");
            page.addTargetRequest("https://house.ifeng.com/news/1-17/");
            page.addTargetRequest("https://house.ifeng.com/news/671-672/");
            page.addTargetRequest("https://house.ifeng.com/news/3-32/");
            page.addTargetRequest("https://house.ifeng.com/news/1-667/");
            page.addTargetRequest("https://house.ifeng.com/news/2-411/");
            page.addTargetRequest("https://house.ifeng.com/news/1-10/");
            page.addTargetRequest("https://house.ifeng.com/news/1-5/");
            page.addTargetRequest("https://house.ifeng.com/news/1-15/");
        } else if (page.getUrl().toString().endsWith("news")) {
            //添加该城市所有栏目的url到爬取队列
            List<String> list = page.getHtml()
                    .xpath("//ul[@id='newsNavScroll']//a/@href").all();
            for (String s : list) {
                page.addTargetRequest(s);
            }
        } else if (page.getUrl().regex(".*\\d+-\\d+/$").match()||page.getUrl().regex("\\D*//\\D*/\\D*/\\D*/").match()) {
            //添加该页面文章的url到爬取队列
            List<String> list = page.getHtml().xpath("//div[@class='ni_list]/a/@href").all();
            for (String s : list) {
                //这里从url的日期中筛选要爬取的文章(昨日的文章)
                String str = s.split("/")[4];
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -1);
                Date time = cal.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
                String date = sdf.format(time);
                if (str.substring(0, 10).equals(date)) page.addTargetRequest(s);
            }
        } else if (page.getUrl().regex(".*\\d{4}_\\d{2}_\\d{2}-\\d*.*\\.shtml$").match()) {
            //获取url
            String url = page.getUrl().toString();
            //获取文章时间
            String date = page.getHtml().xpath("//div[@class='marb-5']/span/text()").toString();
            //获取文章标题
            String title = page.getHtml().xpath("//div[@class='title']/h2/text()").toString();
            //获取文章内容
            String text = page.getHtml().xpath("//div[@class='article']/html()").toString();
            //获取文章来源
            String source = page.getHtml().xpath("//div[@class='pr']/span/text()").toString().substring(3);
            //获取栏目名称
            String type = page.getHtml().xpath("//div[@class='navLink-news']/span/a[3]/text()").toString();
            //获取城市名称
            String city = page.getHtml().xpath("//div[@class='logo-span']//span/text()").toString();
            Article article = new Article();
            article.setText(text);
            article.setTitle(title);
            article.setType(type);
            article.setUrl(url);
            article.setCity(city);
            article.setDate(date);
            article.setSource(source);
            page.putField("article", article);
        }
    }


    @Override
    public Site getSite() {
        return site;
    }
}
