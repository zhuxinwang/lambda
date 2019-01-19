package com.zhuxinwang.lambda.chapter1;


/**
 * @author 易用软件-朱新旺(zhuxinwang@aliyun.com)
 * @date 2019/1/17 0017 9:39
 */
public interface Consumer<T> {
    /**
     * 操作对象
     * @param t 具体对象值
     */
    void accept(T t);
}
