package com.zhuxinwang.lambda.chapter3;

import org.apache.logging.log4j.util.PropertySource;

import java.time.Year;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.zhuxinwang.lambda.chapter3.Topic.*;

/**
 * @author 易用软件-朱新旺(zhuxinwang@aliyun.com)
 * @date 2019/1/17 0017 20:36
 */
public class Main {
    public static void main(String[] args) {
        List<Book> library = init();

        //region 一、流处理的示例

        //region 1.只包含计算的图书的流
        Stream<Book> computingBooks = library.stream().filter(b->b.getTopic() == COMPUTING);
        //endregion

        //region 2.只包含图书标题的流
        Stream<String> bookTitles = library.stream().map(Book::getTitle);
        //endregion

        //region 3.Book的流，根据标题排序
        Stream<Book> booksSortedByTitle = library.stream().sorted(Comparator.comparing(Book::getTitle));
        //endregion

        //region 4.使用这个排序流创建一个作者流，根据图书标题排序，并且去除重复的
        Stream<String> authorsInBookTitleOrder = library.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .flatMap(book -> book.getAuthors().stream())
                .distinct();
        //endregion

        //region 5.以标题的字母顺序生成前100个图书的流
        Stream<Book> readingList = library.stream().sorted(Comparator.comparing(Book::getTitle)).limit(100);
        //endregion

        //region 6.出去前100个图书的流
        Stream<Book> remainderList = library.stream().sorted(Comparator.comparing(Book::getTitle)).skip(100);
        //endregion

        //region 7.图书馆中最早出版的图书
        Optional<Book> oldest = library.stream().min(Comparator.comparing(Book::getPubDate));
        //endregion

        //region 8.图书馆中图书的标题集合
        Set<String> titles = library.stream().map(Book::getTitle).collect(Collectors.toSet());
        //endregion

        //endregion


        //region 1.根据主题对图书进行分类的Map
        Map<Topic,List<Book>> booksByTopic = library.stream().collect(Collectors.groupingBy(Book::getTopic));
        System.out.println("1.根据主题对图书进行分类的Map"+ booksByTopic);
        //endregion

        //region 2.从图书标题映射到最新版发布日期的有序Map
        Map<String,Year> titleToPubDate = library.stream().collect(Collectors.toMap(
                Book::getTitle
                , Book::getPubDate
                , BinaryOperator.maxBy(Comparator.naturalOrder())
                ,TreeMap::new));
        System.out.println("2.从图书标题映射到最新版发布日期的有序Map" + titleToPubDate);
        //endregion

        //region 3.将图书划分为小说（对应true）与非小说（对应false）的Map
        Map<Boolean,List<Book>> fictionOrNon = library.stream().collect(Collectors.partitioningBy(b->b.getTopic() == FICTION));
        System.out.println("3.将图书划分为小说（对应true）与非小说（对应false）的Map" + fictionOrNon);
        //endregion

        //region 4.将每个主题关联到该主题下拥有最多作者的图书上
        Map<Topic,Optional<Book>> mostAuthorsByTopic = library.stream().collect(Collectors.groupingBy(Book::getTopic
                ,Collectors.maxBy(Comparator.comparing(b->b.getAuthors().size()))));
        System.out.println("4.将每个主题关联到该主题下拥有最多作者的图书上" + mostAuthorsByTopic);
        //endregion


        //region 5.将每个主题关联到该主题总的卷数上
        Map<Topic,Integer> volumeCountByTopic = library.stream().collect(
                Collectors.groupingBy(Book::getTopic, Collectors.summingInt(b->b.getPageCounts().length)));
        System.out.println("5.将每个主题关联到该主题总的卷数上" + volumeCountByTopic);
        //endregion

        //region 6.拥有最多的图书主题
        Optional<Topic> mostPopularTopic = library.stream().collect(
                Collectors.groupingBy(Book::getTopic, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        System.out.println("" + mostPopularTopic);
        //endregion

        //region 7.将每个主题关联到该主题下所有图书标题拼接成的字符串上
        Map<Topic,String> concatenatedTitlesByTopic = library.stream().collect(
                Collectors.groupingBy(Book::getTopic,
                        Collectors.mapping(Book::getTitle,Collectors.joining(";"))));
        System.out.println("7.将每个主题关联到该主题下所有图书标题拼接成的字符串上" + concatenatedTitlesByTopic);
        //endregion
    }

    /**
     * 1.初始化
     */
    private static List<Book> init() {
        Book nails = new Book("Fundamentals of Chinese Fingernail Image"
                , Arrays.asList("Li","Fu","Li")
                ,new int[]{256}
                , Year.of(2014)
                ,25.2
                ,MEDICINE);

        Book dragon = new Book("Compilers:Principles,Techniques and Tools"
                , Arrays.asList("Aho","Lam","Sethi","Ullman")
                ,new int[]{1009}
                , Year.of(2006)
                ,23.6
                ,COMPUTING);

        Book voss = new Book("Voss"
                , Arrays.asList("Patrick White")
                ,new int[]{478}
                , Year.of(1957)
                ,19.8
                ,FICTION);

        Book lotr = new Book("Lord of the Rings"
                , Arrays.asList("Tolkien")
                ,new int[]{531,416,624}
                , Year.of(1955)
                ,20.3
                ,FICTION);

        List<Book> bookList = new ArrayList<>();
        bookList.add(nails);
        bookList.add(dragon);
        bookList.add(voss);
        bookList.add(lotr);

        return bookList;
    }


}
