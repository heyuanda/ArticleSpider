package com.meifang.article.dao.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "article")
public class Article {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 栏目
     */
    @Column(name = "type")
    private String type;

    /**
     * 正文
     */
    @Column(name = "text")
    private String text;

    /**
     * url
     */
    @Column(name = "url")
    private String url;

    /**
     * 发布时间
     */
    @Column(name = "date")
    private String date;

    /**
     * 文章来源
     */
    @Column(name = "source")
    private String source;

    /**
     * 所属城市
     */
    @Column(name = "city")
    private String city;



    /**
     * 删除标识
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", date='" + date + '\'' +
                ", source='" + source + '\'' +
                ", city='" + city + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
