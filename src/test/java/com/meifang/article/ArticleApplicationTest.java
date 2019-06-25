package com.meifang.article;


import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ArticleApplicationTest {

    @Test
    public void test(){
        boolean flag="https://sh.house.ifeng.com/news/2019_06_20-52130802_0.shtml".matches("https://sh.house.ifeng.com/news");
        System.out.println(flag);
    }
}