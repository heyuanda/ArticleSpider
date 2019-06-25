package com.meifang.article.service;

import com.github.pagehelper.PageHelper;
import com.meifang.article.dao.domain.Article;
import com.meifang.article.dao.mapper.ArticleMapper;
import com.meifang.article.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public boolean insert(Article article) {
        return articleMapper.insert(article) == 1 ? true : false;
    }

    @Override
    public PageBean<Article> getArticle(int currentPage, int pageSize,Article article) {
        //设置分页信息，分别是当前页数和每页显示的总记录数
        PageHelper.startPage(currentPage, pageSize);

        List<Article> articles = articleMapper.selectByArticle(article);
        int countNums = articleMapper.getCount(article);            //总记录数
        PageBean<Article> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setData(articles);
        return pageData;
    }


    @Override
    public Article findArticleById(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }
}
