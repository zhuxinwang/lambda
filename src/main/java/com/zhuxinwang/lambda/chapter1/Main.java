package com.zhuxinwang.lambda.chapter1;


import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 易用软件-朱新旺(zhuxinwang@aliyun.com)
 * @date 2019/1/17 0017 9:35
 */
public class Main {

    /**
     * 1.Lambda表达式
     */
    private static void lambda() {
        List<Point> pointList = Arrays.asList(new Point(1,2), new Point(2,1));
        pointList.forEach(p->p.translate(1,1));
    }

    /**
     * 2.Stream流1
     */
    private static void stream1() {
        List<Integer> intList = Arrays.asList(1,2,3,4,5);
        List<Point> pointList1 = new ArrayList<>();
        for (Integer integer : intList){
            pointList1.add(new Point(integer % 3,integer));
        }
        double maxDistance = Double.MIN_VALUE;
        for(Point point : pointList1){
            maxDistance = Math.max(point.distance(0,0),maxDistance);
        }

        System.out.println(maxDistance);
    }

    /**
     * 3.stream风格代码
     * @param intList  数组参数
     */
    private static void stream(List<Integer> intList) {
        OptionalDouble maxDistance = intList.stream()
                .map(i->new Point(i%3,i))
                .mapToDouble(p->p.distance(0,0))
                .max();
        System.out.println(maxDistance);
    }

    public static void main(String[] args) {
//        lambda();

        List<Integer> intList = Arrays.asList(1,2,3,4,5);

        stream(intList);
    }




}
