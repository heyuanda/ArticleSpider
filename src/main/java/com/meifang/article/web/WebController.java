package com.meifang.article.web;

import com.meifang.article.dao.domain.Article;
import com.meifang.article.service.ArticleService;
import com.meifang.article.service.NewPipeline;
import com.meifang.article.util.PageBean;
import com.meifang.article.util.ResponseResult;
import com.meifang.article.webMagic.HttpClientDownloader;
import com.meifang.article.webMagic.IfengProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;

import java.util.List;

@Controller
public class WebController {
    private Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private ArticleService articleService;

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @PostMapping("/show_article/{page}")
    @ResponseBody
    public ResponseResult<PageBean<Article>> showArticle(@PathVariable("page") Integer page,Article article) {
        PageBean<Article> articleList =articleService.getArticle(page,18,article);
        return new ResponseResult<PageBean<Article>>(1,articleList);
    }

    @RequestMapping("/detail")
    public String showDetail() {
        return "detail";
    }

    @RequestMapping("/article/detail/{id}")
    @ResponseBody
    public ResponseResult<Article> detail(@PathVariable("id") Integer id) {
        Article article = articleService.findArticleById(id);
        if (article == null) return new ResponseResult<>(0);
        return new ResponseResult<Article>(1, article);
    }

    @RequestMapping("webmagic")
    public String webmagic() {
        logger.info("开始爬虫");
        Spider spider = Spider.create(new IfengProcessor()).setDownloader(new HttpClientDownloader());
        spider.addUrl("https://sh.house.ifeng.com/news");
        spider.addPipeline(new NewPipeline());
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
        spider.stop();
        logger.info("结束爬虫");
        return "index";
    }
}
