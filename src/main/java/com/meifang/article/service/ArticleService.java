package com.meifang.article.service;

import com.meifang.article.dao.domain.Article;
import com.meifang.article.util.PageBean;


public interface ArticleService{
    boolean insert(Article article);

    PageBean<Article> getArticle(int currentPage, int pageSize, Article article);

    Article findArticleById(Integer id);
}
