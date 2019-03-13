package com.zengshi.ecp.search.dubbo.search.comp.interpreter;

import com.zengshi.ecp.search.dubbo.search.SearchException;

/**
 * Created by HDF on 2016/10/8.
 */
public class Constant extends Expression {

    private boolean value;

    public Constant(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof Constant) {
            return this.value == ((Constant) obj).value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    // 解释常量值
    @Override
    public boolean interpret(Context ctx) throws SearchException {
        return value;
    }

    // 显示常量字符串表示
    @Override
    public String toString() {
        return new Boolean(value).toString();
    }

    // 文法表示
    @Override
    public String grammar(Context ctx) throws SearchException{
        return this.toString();
    }

}
