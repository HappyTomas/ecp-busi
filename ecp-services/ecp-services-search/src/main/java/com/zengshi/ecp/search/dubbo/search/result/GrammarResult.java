package com.zengshi.ecp.search.dubbo.search.result;

import java.io.Serializable;

/**
 * Created by HDF on 2016/10/8.
 */
public class GrammarResult implements Serializable {

    /**
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 1L;

    /**
     * 错误或提示信息
     */
    private String message;

    /**
     * 处理过程是否成功
     */
    private boolean ifSuccess=false;

    /**
     * 文法
     */
    private String grammar;

    public boolean isIfSuccess() {
        return ifSuccess;
    }

    public void setIfSuccess(boolean ifSuccess) {
        this.ifSuccess = ifSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGrammar() {
        return grammar;
    }

    public void setGrammar(String grammar) {
        this.grammar = grammar;
    }

    @Override
    public String toString() {
        return "GrammarResult{" +
                "grammar='" + grammar + '\'' +
                ", message='" + message + '\'' +
                ", ifSuccess=" + ifSuccess +
                '}';
    }
}
