package com.zengshi.ecp.search.dubbo.search;

import com.zengshi.ecp.search.dubbo.search.comp.interpreter.Expression;
import com.zengshi.ecp.search.dubbo.search.util.ELang;

/**
 * Created by HDF on 2016/9/22.
 */
public class GrammarParam {

    /**
     * 站点Id
     */
    private Long currentSiteId = -1L;

    /**
     * 集合名称，由站点Id配置路由生成
     */
    private String collectionName;

    /**
     * 语种，用于多语言查询。多语言环境下目前只支持单语言查询（返回某种语言下的数据）。
     */
    private ELang lang;

    /**
     * 表达式
     */
    private Expression exp;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Long getCurrentSiteId() {
        return currentSiteId;
    }

    public void setCurrentSiteId(Long currentSiteId) {
        this.currentSiteId = currentSiteId;
    }

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    public ELang getLang() {
        return lang;
    }

    public void setLang(ELang lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "GrammarParam{" +
                "collectionName='" + collectionName + '\'' +
                ", currentSiteId=" + currentSiteId +
                ", lang='" + lang + '\'' +
                ", exp=" + exp +
                '}';
    }
}
