package com.meifang.article.dao.mapper;

import com.meifang.article.dao.domain.Article;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface ArticleMapper extends Mapper<Article> {
    Integer getCount(Article article);
    List<Article> selectByArticle(Article article);
}
