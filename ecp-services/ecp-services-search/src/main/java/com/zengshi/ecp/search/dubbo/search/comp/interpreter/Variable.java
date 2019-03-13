package com.zengshi.ecp.search.dubbo.search.comp.interpreter;

import com.zengshi.ecp.search.dubbo.search.SearchException;

/**
 * Created by HDF on 2016/10/8.
 */
public class Variable extends Expression {

    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof Variable) {
            return this.name.equals(((Variable) obj).name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    // 显示变量字符串表示
    @Override
    public String toString() {
        return name;
    }

    // 文法表示
    @Override
    public String grammar(Context ctx) throws SearchException{
        return this.toString();
    }

    // 解释变量值
    @Override
    public boolean interpret(Context ctx) throws SearchException {
        return ctx.lookup(this);
    }

}
