package com.zengshi.ecp.search.dubbo.search.comp.interpreter;

import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.search.util.ELang;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HDF on 2016/10/8.
 */
public class Context {

    private Map<Variable,Boolean> map = new HashMap<Variable,Boolean>();

    //属性名2索引字段名映射
    private Map<String, String> beanFieldName2IndexNameMap;

    //多语言字段map
    private Map<String,String> multiLangMap;

    //语种，用于多语言查询。多语言环境下目前只支持单语言查询。
    private ELang lang;

    public Context(Map<String, String> beanFieldName2IndexNameMap,Map<String,String> multiLangMap){
        this.beanFieldName2IndexNameMap=beanFieldName2IndexNameMap;
        this.multiLangMap=multiLangMap;
    }

    public Map<String, String> getBeanFieldName2IndexNameMap() {
        return beanFieldName2IndexNameMap;
    }

    public Map<String, String> getMultiLangMap() {
        return multiLangMap;
    }

    public ELang getLang() {
        return lang;
    }

    public void setLang(ELang lang) {
        this.lang = lang;
    }

    //变量赋值
    public void assign(Variable var , boolean value){
        map.put(var, new Boolean(value));
    }

    //获取变量值/解释变量值
    public boolean lookup(Variable var) throws SearchException{
        Boolean value = map.get(var);
        if(value == null){
            throw new SearchException("标量未赋值："+var.getName());
        }
        return value.booleanValue();
    }

}
