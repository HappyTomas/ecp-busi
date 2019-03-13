package com.zengshi.ecp.search.dubbo.search.comp.interpreter;

import com.zengshi.ecp.search.dubbo.search.SearchException;

/**
 * Created by HDF on 2016/10/8.
 */
public abstract class Expression {

    /**
     * 以环境为准，本方法解释给定的任何一个表达式
     */
    public abstract boolean interpret(Context ctx) throws SearchException;

    /**
     * 检验两个表达式在结构上是否相同
     */
    public abstract boolean equals(Object obj);

    /**
     * 返回表达式的hash code
     */
    public abstract int hashCode();

    /**
     * 将表达式转换成字符串
     */
    public abstract String toString();

    /**
     * 文法表示
     */
    public abstract String grammar(Context ctx) throws SearchException;

}
