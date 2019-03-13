package com.zengshi.ecp.search.dubbo.search;

import com.zengshi.ecp.search.dubbo.search.comp.interpreter.Expression;
import com.zengshi.ecp.search.dubbo.search.result.binder.BaseResultBinder;
import com.zengshi.ecp.search.dubbo.search.util.ELang;

import java.util.List;
import java.util.Map;

/**
 * Created by HDF on 2016/9/22.
 */
public class RecommendParam {

    /*---------------------------------CF推荐 或 基于用户画像的CB推荐（首页）start-------------------------------------*/

    /**
     * 用户编码：用户编码即是基于协同过滤CF的推荐。
     */
    private String userId;

    /*---------------------------------CF推荐 end-------------------------------------*/





    /*---------------------------------CB推荐（详情页） start-------------------------------------*/

    /**
     * 集合名称，直接指定或由站点Id配置路由生成
     */
    private String collectionName;

    // CB推荐支持两种推荐规则指定方式。第一种是，传入推荐依据数据，搜索引擎根据配置的推荐转换模式生成检索推荐语法。
    // 第二种是，支持传入自定义文法抽象对象实现，这是一种更为灵活的实现（优先级最高）。

    /**
     * 1、检索物品对应的字段属性数据：即基于物品的CB推荐
     * 2、基于物品的CB推荐，物品字段信息是可以强制要求来源于详情页信息的。同时也是为了避免在推荐过程中重复信息查询的问题。基于物品的CB推荐需要指定物品对应的字段属性数据（包含推荐依据字段列表值即可）。
     * 3、如果是二级分页的索引配置，则物品编码需要同时传入内部和外部编码。使用SearchConstants.SEPERATOR间隔
     */
    private Map<String, Object> itemData=null;

    /**
     * 检索推荐表达式，优先级最高（即可用于CB推荐（详情页），也可用于基于用户画像的CB推荐（首页））
     */
    private Expression exp;

    /*---------------------------------CB推荐 end-------------------------------------*/

    /**
     * 返回结果数据绑定类型
     */
    private BaseResultBinder.EBinderType binderType=BaseResultBinder.EBinderType.MAP;

    /**
     * 返回字段列表。搜索引擎返回物品字段列表。
     */
    private List<String> fieldList;

    /**
     * 返回Bean实例类型
     */
    @SuppressWarnings("rawtypes")
    private Class clazz;

    /**
     * 当前页
     */
    private long pageNo=1;

    /**
     * 搜索结果pageSize平台统一，由前店后端负责参数初始化
     */
    private int pageSize = 10;

    /**
     * 语种，用于多语言查询。
     */
    private List<ELang> langs;

    /**
     * 默认搜索字段，解决多关键字检索无法出结果问题
     */
    private String df;

    /**
     * 字段排序List，对于检索推荐来说，可能会存在按照自定义排序方式返回检索推荐结果的需求
     */
    private List<SortField> sortFieldList;

    /**
     * 是否按评分排序
     */
    private boolean ifSortByScore=false;

    /**
     * 评分字段排序位置，默认0最前面。
     */
    private int scorePosition=0;

    /**
     * 范围查询条件列表，需要注意的是，范围查询会影响*Facet统计结果
     */
    private List<RangeQueryField> rangeQueryFieldList;

    /**
     * Minimum 'Should' Match
     * mm:值可以使正正数，负整数，正的百分数，负的百分数。正数表示分析器分词后必须出现的个数，负数表示可以不出现词语的个数
     */
    private String mm="";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Object> getItemData() {
        return itemData;
    }

    public void setItemData(Map<String, Object> itemData) {
        this.itemData = itemData;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
        this.binderType=BaseResultBinder.EBinderType.BEAN;
    }

    public List<String> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<String> fieldList) {
        this.fieldList = fieldList;
        this.binderType=BaseResultBinder.EBinderType.MAP;
    }

    public List<ELang> getLangs() {
        return langs;
    }

    public void setLangs(List<ELang> langs) {
        this.langs = langs;
    }

    public String getDf() {
        return df;
    }

    public void setDf(String df) {
        this.df = df;
    }

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<SortField> getSortFieldList() {
        return sortFieldList;
    }

    public void setSortFieldList(List<SortField> sortFieldList) {
        this.sortFieldList = sortFieldList;
    }

    public BaseResultBinder.EBinderType getBinderType() {
        return binderType;
    }

    public boolean isIfSortByScore() {
        return ifSortByScore;
    }

    public void setIfSortByScore(boolean ifSortByScore) {
        this.ifSortByScore = ifSortByScore;
    }

    public void setIfSortByScore(boolean ifSortByScore,int scorePosition) {
        this.ifSortByScore = ifSortByScore;
        this.scorePosition = scorePosition;
    }

    public int getScorePosition() {
        return scorePosition;
    }

    public void setScorePosition(int scorePosition) {
        this.scorePosition = scorePosition;
    }

    public List<RangeQueryField> getRangeQueryFieldList() {
        return rangeQueryFieldList;
    }

    public void setRangeQueryFieldList(List<RangeQueryField> rangeQueryFieldList) {
        this.rangeQueryFieldList = rangeQueryFieldList;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    @Override
    public String toString() {
        return "RecommendParam{" +
                "binderType=" + binderType +
                ", userId='" + userId + '\'' +
                ", collectionName='" + collectionName + '\'' +
                ", itemData=" + itemData +
                ", fieldList=" + fieldList +
                ", clazz=" + clazz +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
