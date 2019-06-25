package com.meifang.article.service;

import com.meifang.article.SpringApplicationContext;
import com.meifang.article.dao.domain.Article;
import com.meifang.article.dao.mapper.ArticleMapper;
import com.meifang.article.service.Exception.InsertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

@Service("NewPipeline")
public class NewPipeline implements Pipeline {
    private Logger logger= LoggerFactory.getLogger(NewPipeline.class);

    private ArticleMapper articleMapper;

    public NewPipeline(){
        this.articleMapper=SpringApplicationContext.getBean(ArticleMapper.class);
    }
    @Override
    public void process(ResultItems resultItems, Task task) {
        for(Map.Entry<String,Object> entry:resultItems.getAll().entrySet()){
            if (entry.getKey().contains("article")){
                //此处做去重判断
                Article article=(Article)entry.getValue();
                Article atl=new Article();
                atl.setUrl(article.getUrl());
                if(articleMapper.select(atl).size()>0)continue;
                if(articleMapper.insert((Article) entry.getValue())!=1)throw new InsertException();
            }
        }
    }
}
