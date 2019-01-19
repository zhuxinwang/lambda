package com.zhuxinwang.lambda.chapter2;

import lombok.Data;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 易用软件-朱新旺(zhuxinwang@aliyun.com)
 * @date 2019/1/17 0017 11:00
 */
public class Main {

    public static void main(String[] args) {
//        Object i = (Supplier)()->23;

//        System.out.println(i);


//        IntStream.iterate(1,i->i*2).limit(10).forEachOrdered(System.out::println);

        //region lambda表达式遍历
        List<String> list = new ArrayList<>();
        list.add("朱");
        list.add("新");
        list.add("旺");

        list.forEach(System.out::println);
        //endregion

        Book book1 = new Book("三体",52);
        Book book2 = new Book("你的孤独，虽败犹荣",70);
        List<Book> bookList = new ArrayList<>(2);
        bookList.add(book1);
        bookList.add(book2);

        Optional<Book> optionalBook = bookList.stream().findFirst();

        Map<String,Integer> bookMap = bookList.stream().collect(Collectors.toMap(Book::getName,Book::getPrice));

        System.out.println(bookMap.toString());


        String nameTitle = bookList.stream().map(Book::getName).collect(Collectors.joining("::"));
        System.out.println(nameTitle);

        Object o = bookList.stream().map(Book::getPrice).toArray();
        System.out.println(o);


        List<String> stringList = bookList.stream().map(Book::getName).collect(Collectors.toList());
        System.out.println(stringList);

        Set<String> set = new HashSet<>();


        System.out.println(bookList.stream().mapToInt(Book::getPrice).sum());
    }


    public void testReturn(){
        System.out.println("heiheihei");
        return;
    }
}

@Data
class Book{
    Book(String name, int price){
        this.name = name;
        this.price = price;
    }
    private String name;
    private int price;
}
