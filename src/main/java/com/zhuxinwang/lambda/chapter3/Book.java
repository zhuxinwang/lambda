package com.zhuxinwang.lambda.chapter3;

import lombok.Data;

import java.time.Year;
import java.util.List;

/**
 * 图书类
 * @author 易用软件-朱新旺(zhuxinwang@aliyun.com)
 * @date 2019/1/17 0017 20:33
 */
@Data
public class Book {

    Book(String title, List<String> authors, int[] pageCounts, Year pubDate, double height, Topic topic) {
        this.title = title;
        this.authors = authors;
        this.pageCounts = pageCounts;
        this.pubDate = pubDate;
        this.height = height;
        this.topic = topic;
    }

    /**
     * 书名
     */
    private String title;

    /**
     * 作者列表
     */
    private List<String> authors;

    /**
     * 卷的页数
     */
    private int[] pageCounts;

    /**
     * 出版日期
     */
    private Year pubDate;

    /**
     * 高度
     */
    private double height;

    /**
     * 书的类型
     */
    private Topic topic;
}
